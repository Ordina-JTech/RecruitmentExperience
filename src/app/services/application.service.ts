import { Injectable } from '@angular/core';
import { Application } from '../interfaces/application';
import APPLICATIONS from '../../mock-data/applications';
import { mockPromise } from './helpers';

@Injectable({
  providedIn: 'root'
})
export class ApplicationService {

  constructor() { }

  getApplications(state: string): Promise<Application[]> {
    return mockPromise(() => {
      return APPLICATIONS.filter(application => application.state === state);
    });
  }

  getApplication(id: number): Promise<Application> {
    return mockPromise(() => APPLICATIONS.find(application => application.id === id));
  }
}
 