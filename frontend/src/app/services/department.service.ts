import { Injectable } from '@angular/core';
import { Cacheable } from 'ngx-cacheable';
import { Department } from '../definitions/department';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  constructor(private api: ApiService) { }

  @Cacheable()
  getDepartments(): Observable<Department[]> {
    return this.api.get(`departments`);
  }

  getDepartment(id: number): Observable<Department> {
    return new Observable(subscriber => {
      this.getDepartments().subscribe(departments => {
        subscriber.next(departments.find(region => region.id === id));
        subscriber.complete();
      });
    });
  }
}
