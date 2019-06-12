import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApplicationService } from '../services/application.service';
import { Application } from '../interfaces/application';
import { ModalService } from '../services/modal.service';

@Component({
  selector: 'app-application-list',
  templateUrl: './application-list.component.html',
  styleUrls: ['./application-list.component.scss']
})
export class ApplicationListComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, 
              private applicationsService: ApplicationService,
              private modal: ModalService
              ) { }

  applications: Application[] = [];
  _filterState: string = null;
  displayedColumns: string[] = [
    'applicant',
    'department',
    // 'bum',
    'title',
  ];

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
  }

  handleAddClick = () => {

  };
}
