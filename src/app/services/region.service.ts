import { Injectable } from '@angular/core';
import { Cacheable } from 'ngx-cacheable';
import { Observable } from 'rxjs';
import { Region } from '../definitions/region';
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
    return new Observable(subscriber => {
      this.getRegions().subscribe(regions => {
        subscriber.next(regions.find(region => region.id === id));
        subscriber.complete();
      });
    });
  }
}
