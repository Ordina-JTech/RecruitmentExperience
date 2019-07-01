import { Injectable } from '@angular/core';
import { ConfigService } from './config.service';
import { Observable } from 'rxjs';
import resolveMockData from '../../mock-data';

interface MockResolver {
  path: string;
  result: string;
}

@Injectable({
  providedIn: 'root'
})
export class MockService {

  constructor(private config: ConfigService) { }

  get<T>(path: string): Observable<T> {
    return this.mockObservable<T>(() => resolveMockData<T>(path));
  }

  mockObservable<T>(func: () => any, mockLag: number = this.config.getMockLag()): Observable<T> {
    return new Observable<T>(subscriber => {
      setTimeout(() => {
        try {
          subscriber.next(func());
          subscriber.complete();
        } catch (err) {
          subscriber.error(err);
        }
      }, mockLag);
    });
  }
}
