import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Application } from '../interfaces/application';
import { ApiService } from './api.service';
import {map} from 'rxjs/operators'
import { ApplicationStates } from '../interfaces/application-states.enum';
import { Note } from '../interfaces/note';
import { ApplicationCounts } from '../interfaces/application-counts';

@Injectable({
  providedIn: 'root'
})
export class ApplicationService {

  constructor(private api: ApiService) { }

  getApplicationCounts(): Observable<ApplicationCounts> {
    return this.api.get(`application-states`).pipe(map(this.formatApplicationCounts));
  }

  getApplications(state: string): Observable<Application[]> {
    return this.api.get<Application[]>(`applications?state=${state}`).pipe(map(applications => applications.map(this.formatApplication)));
  }

  getApplication(applicationId: number): Observable<Application> {
    return this.api.get<Application>(`applications/${applicationId}`).pipe(map(this.formatApplication));
  }

  getApplicationNotes(applicationId: number): Observable<Note[]> {
    return this.api.get(`applications/${applicationId}/notes`);
  }

  private formatApplicationCounts(applicationCounts: ApplicationCounts): ApplicationCounts {
    const keys = Object.keys(applicationCounts);
    const result = {};
    keys.forEach(key => result[key.toLowerCase()] = applicationCounts[key]);
    return <ApplicationCounts>result;
  }

  // De backend stuurt de application states in uppercase op
  private formatApplication(application: Application) {
    return {
      ...application,
      state: <ApplicationStates>application.state.toLowerCase(),
    }
  }
}
