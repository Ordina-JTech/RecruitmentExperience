import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApplicationService } from '../services/application.service';
import { Application } from '../interfaces/application';
import { ModalService } from '../services/modal.service';
import { Department } from '../interfaces/department';
import { DepartmentService } from '../services/department.service';

@Component({
  selector: 'app-application-list',
  templateUrl: './application-list.component.html',
  styleUrls: ['./application-list.component.scss']
})
export class ApplicationListComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute,
              private applicationsService: ApplicationService,
              private modalService: ModalService,
              private departmentService: DepartmentService
              ) { }

  applications: Application[] = [];
  _filterState: string = null;
  displayedColumns: string[] = [
    'applicant',
    'department',
    // 'bum',
    'title',
  ];

  departments: Department[] = null;

  get filterState(): string {
    return this._filterState;
  }
  set filterState(value: string) {
    this._filterState = value;

    this.reloadData();
  }

  async reloadData() {
    this.applications = await this.applicationsService.getApplications(this.filterState).toPromise();
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      this.filterState = params.state;
    });

    this.loadDepartments();
  }

  async loadDepartments() {
    this.departments = await this.departmentService.getDepartments().toPromise();
  }

  getDepartmentName(id: number): string {
    const department = this.departments.find(d => d.id === id);

    if (department) {
      return department.name;
    } else {
      return '';
    }
  }

  handleAddClick = () => {

  }
}
