import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cacheable } from 'ngx-cacheable';

import { ApiService } from './api.service';
import { BusinessUnit } from '../definitions/business-unit';

@Injectable({
  providedIn: 'root'
})
export class BuService {

  constructor(private api: ApiService) { }

  @Cacheable()
  getBUs(): Observable<BusinessUnit[]> {
    return this.api.get(`bus`);
  }

  getBU(id: number): Observable<BusinessUnit> {
    return new Observable(subscriber => {
      this.getBUs().subscribe(bums => {
        subscriber.next(bums.find(bum => bum.id === id));
        subscriber.complete();
      })
    });
  }
}
