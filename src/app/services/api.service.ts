import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ConfigService } from './config.service';
import { MockService } from './mock.service';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient, private config: ConfigService, private mock: MockService) { }

  get<T>(path: string): Observable<T> {
    if (!this.config.isMockMode()) {
      return this.http.get<T>(this.config.getBaseUrl() + path, {});
    } else {
      return this.mock.get<T>(path);
    }
  }
}
