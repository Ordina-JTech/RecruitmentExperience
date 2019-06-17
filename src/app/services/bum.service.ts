import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cacheable } from 'ngx-cacheable';

import { BusinessUnitManager } from '../definitions/business-unit-manager';
import { ApiService } from './api.service';


@Injectable({
  providedIn: 'root'
})
export class BumService {

  constructor(private api: ApiService) { }

  @Cacheable()
  getBUMs(): Observable<BusinessUnitManager[]> {
    return this.api.get(`business-unit-managers`);
  }

  @Cacheable()
  getBUM(id: number): Observable<BusinessUnitManager> {
    return this.api.get(`business-unit-managers/${id}`);
  }
}
