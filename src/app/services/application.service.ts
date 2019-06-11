import { Injectable } from '@angular/core';
import { Application } from '../interfaces/application';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApplicationService {

  constructor(private api: ApiService) { }

  getApplications(state: string): Observable<Application[]> {
    return this.api.get(`applications?state=${state}`);
  }

  getApplication(id: number): Observable<Application> {
    return this.api.get(`applications/${id}`);
  }
}
 