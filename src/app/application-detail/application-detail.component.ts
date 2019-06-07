import { Component, OnInit } from '@angular/core';
import { ApplicationService } from '../application.service';
import { Application } from '../interfaces/application';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-application-detail',
  templateUrl: './application-detail.component.html',
  styleUrls: ['./application-detail.component.scss']
})
export class ApplicationDetailComponent implements OnInit {

  constructor(private applicationService: ApplicationService,
              private currentRoute: ActivatedRoute) { }

  application: Application = null;
  private _applicationId: number;
  public get applicationId(): number {
    return this._applicationId;
  }
  public set applicationId(value: number) {
    this._applicationId = value;

    this.loadApplication();
  }

  ngOnInit() {
    this.currentRoute.params.subscribe(params => this.applicationId = parseInt(params.applicationId));
  }

  async loadApplication() {
    this.application = await this.applicationService.getApplication(this.applicationId);
  }
}
