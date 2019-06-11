import { Injectable } from '@angular/core';
import { Cacheable } from 'ngx-cacheable';
import { Observable } from 'rxjs';
import { Region } from '../interfaces/region';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class RegionService {

  constructor(private api: ApiService) { }

  @Cacheable()
  getRegions(): Observable<Region[]> {
    return this.api.get(`regions`);
  }

  getRegion(id: number): Observable<Region> {
    return new Observable(region => {
      this.getRegions().subscribe(regions => {
        console.log('region ' + id, regions.find(region => region.id === id))
        region.next(regions.find(region => region.id === id));
        region.complete();
      });
    });
  }
}
