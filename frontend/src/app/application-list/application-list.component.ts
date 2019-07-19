import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ApplicationService } from '../services/application.service';
import { Application } from '../definitions/application';
import { Department } from '../definitions/department';
import { DepartmentService } from '../services/department.service';
import { ApplicationState } from '../definitions/application-states.enum';
import { BusinessUnitManager } from '../definitions/business-unit-manager';
import { BumService } from '../services/bum.service';

@Component({
  selector: 'app-application-list',
  templateUrl: './application-list.component.html',
  styleUrls: ['./application-list.component.scss']
})
export class ApplicationListComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute,
              private applicationService: ApplicationService,
              private departmentService: DepartmentService,
              private bumService: BumService,
              private router: Router,
              ) { }

  departments: Department[] = null;
  businessUnitManager: BusinessUnitManager[] = null;
  applications: Application[] = [];
  _filterState: string = null;
  displayedColumns: string[] = [
    'applicant',
    'department',
     'bum',
    'title',
  ];

  get filterState(): string {
    return this._filterState;
  }
  set filterState(value: string) {
    this._filterState = value.toUpperCase();

    this.loadApplications();
  }

  async loadApplications() {
    this.applications = await this.applicationService.getApplications(this.filterState).toPromise();
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      this.filterState = params.state;
    });

    this.getDepartments();
    this.getBusinessUnitManager();
  }

  async getDepartments() {
    return this.departments = await this.departmentService.getDepartments().toPromise();
  }

  async getBusinessUnitManager() {
    return this.businessUnitManager = await this.bumService.getBUMs().toPromise();
  }

  getDepartmentName(id: number): string {
    const department = this.departments.find(d => d.id === id);

    if (department) {
      return department.name;
    } else {
      return '';
    }
  }

  handleAddClick = async () => {
    const application = await this.applicationService.openCreateModal();

    if (application) {
      this.router.navigateByUrl(`applications/${ApplicationState.NEW}/${application.id}`);
    }
  }

  getBusinessUnitManagerName(id: number): string {
    const businessUintManager = this.businessUnitManager.find(d => d.id === id);
    if (businessUintManager) {
      return businessUintManager.firstName + " " + businessUintManager.prefix + " " + businessUintManager.lastName;
    } else {
      return '';
    }
  }
}
