import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Application } from '../interfaces/application';
import { ApiService } from './api.service';
import { BumService } from './bum.service';
import { BuService } from './bu.service';


@Injectable({
  providedIn: 'root'
})
export class ApplicationService {

  constructor(private api: ApiService, private bum: BumService, private bu: BuService) { }

  getApplications(state: string): Observable<Application[]> {
    return this.api.get(`applications?state=${state}`);
  }

  getApplication(id: number): Observable<Application> {
    return this.api.get<Application>(`applications/${id}`);
    //   return new Observable(subscriber => {
    //     this.api.get<Application>(`applications/${id}`).subscribe(async application => {
    //       const bum = typeof application.businessUnitManager !== 'number' ? application.businessUnitManager : await this.bum.getBUM(<number>application.businessUnitManager).toPromise();
    //       const bu = typeof application.businessUnit !== 'number' ? application.businessUnit : await this.bu.getBU(<number>application.businessUnit).toPromise();
    //       subscriber.next(<Application> {
    //         ...application,
    //         businessUnitManager: bum,
    //         businessUnit: bu,
    //       });
    //       subscriber.complete();
    //     });
    //   });
    // }
  }
}
