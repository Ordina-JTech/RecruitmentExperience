import { Injectable } from '@angular/core';
import { BusinessUnitManager } from '../interfaces/business-unit-manager';
import BUMS from 'src/mock-data/bums';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BumService {

  constructor(private api: ApiService) { }

  getBUMs(): Observable<BusinessUnitManager[]> {
    return this.api.get(`bums`);
  }

  getBUM(id: number): Observable<BusinessUnitManager> {
    return this.api.get(`bums/${id}`);
  }
}
