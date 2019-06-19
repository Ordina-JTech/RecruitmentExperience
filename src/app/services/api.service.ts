import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ConfigService } from './config.service';
import { MockService } from './mock.service';

export enum RequestType {
  POST,
  GET,
  PUT,
  DELETE,
  RAW,
}

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient, private config: ConfigService, private mock: MockService) { }

  get<T>(path: string): Observable<T> {
    return this._wrapRequest(RequestType.GET, path);
  }

  post<T>(path: string, body: any): Observable<T> {
    return this._wrapRequest(RequestType.POST, path, body);
  }

  put<T>(path: string, body: any): Observable<T> {
    return this._wrapRequest(RequestType.PUT, path, body);
  }

  delete<T>(path: string): Observable<T> {
    return this._wrapRequest(RequestType.DELETE, path);
  }

  upload<T>(path: string, body: any, file: File): any {
    const url = this.config.getBaseUrl() + path;

    const formData: FormData = new FormData();

    // formData.append('file', file);
    Object.keys(body).forEach(key => formData.append(key, body[key]));

    const req = new HttpRequest('POST', url, formData, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);
  }

  private _wrapRequest<T>(requestType: RequestType, path: string, body?: any): Observable<T> {
    if (!this.config.isMockMode()) {
      const url = this.config.getBaseUrl() + path;

      switch (requestType) {
        case RequestType.GET: return this.http.get<T>(url);
        case RequestType.POST: return this.http.post<T>(url, body);
        case RequestType.PUT: return this.http.put<T>(url, body);
        case RequestType.DELETE: return this.http.delete<T>(url);
        default: {
          throw new Error(`Unsupported request type`);
        }
      }
    } else {
      switch (requestType) {
        case RequestType.GET: return this.mock.get<T>(path);
        default: {
          // Observable met lege response, om gewoon een 204 status te simuleren
          return new Observable<T>(subscriber => subscriber.complete());
        }
      }
    }
  }
}
