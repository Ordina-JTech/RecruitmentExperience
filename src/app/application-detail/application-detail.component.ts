import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ApplicationService } from '../services/application.service';
import { Application } from '../definitions/application';
import { ActivatedRoute } from '@angular/router';
import { ApplicationState } from '../definitions/application-states.enum';
import { BusinessUnit } from '../definitions/business-unit';
import { BusinessUnitManager } from '../definitions/business-unit-manager';
import { BuService } from '../services/bu.service';
import { BumService } from '../services/bum.service';
import { Observable } from 'rxjs';
import { Region } from '../definitions/region';
import { RegionService } from '../services/region.service';

import { confetti } from 'dom-confetti';

import {all} from 'bluebird';
import { MessagingService } from '../services/messaging.service';
import { MatDialog } from '@angular/material';
import { EditDialogComponent } from '../dialogs/edit-dialog/edit-dialog.component';
import { FieldType } from '../definitions/edit-dialog';
import { DocumentService } from '../services/document.service';
@Component({
  selector: 'app-application-detail',
  templateUrl: './application-detail.component.html',
  styleUrls: ['./application-detail.component.scss']
})
export class ApplicationDetailComponent implements OnInit {

  constructor(private applicationService: ApplicationService,
              private buService: BuService,
              private bumService: BumService,
              private regionService: RegionService,
              private currentRoute: ActivatedRoute,
              private messagingService: MessagingService,
              private dialog: MatDialog,
              private documentService: DocumentService) { }

  @ViewChild('statusText', {static: true})
  statusText: ElementRef;

  application: Application = null;
  bu: Observable<BusinessUnit> = null;
  bum: Observable<BusinessUnitManager> = null;
  region: Observable<Region> = null;

  private _applicationId: number;
  public get applicationId(): number {
    return this._applicationId;
  }
  
  public set applicationId(value: number) {
    this._applicationId = value;

    this.loadApplication();
  }

  getApplicationProgress(): number {
    const states = Object.values(ApplicationState);
    return (states.findIndex(state => state === this.application.state)) / (states.length - 1) * 100;
  }

  ngOnInit() {
    this.currentRoute.params.subscribe(params => this.applicationId = parseInt(params.applicationId, 10));
  }

  async loadApplication() {
    this.application = await this.applicationService.getApplication(this.applicationId).toPromise();
    this.loadDependencies();
  }

  async loadDependencies() {
    const [bu, bum, region] = await all([
      this.buService.getBU(this.application.businessUnitId),
      this.bumService.getBUM(this.application.businessUnitManagerId),
      this.regionService.getRegion(this.application.regionId),
    ]);

    this.bu = bu;
    this.bum = bum;
    this.region = region;
  }

  promoteApplication = async () => {
    if (this.application.state === ApplicationState.NEW) {
      await this.updateInterviewDate('firstInterviewDateTime');
    } else if (this.application.state === ApplicationState.FIRST_INTERVIEW) {
      await this.updateInterviewDate('secondInterviewDateTime');
    }

    this.application = await this.applicationService.promoteApplication(this.application).toPromise();
    this.messagingService.push({type: 'RELOAD_APPLICATION_COUNTS'});
    if (this.application.state === ApplicationState.SIGNED) {
      confetti(document.getElementById('statusText'));
    }
  }

  async updateInterviewDate(interviewDate: 'firstInterviewDateTime' | 'secondInterviewDateTime') {
    const label = interviewDate === 'firstInterviewDateTime' ? 'Datum eerste interview' : 'Datum tweede interview';
    
    const dialogRef = this.dialog.open(EditDialogComponent, {
      width: '500px',
      data: {
        date: {type: FieldType.Date, label},
      }
    });

    const result = await dialogRef.afterClosed().toPromise();

    if (result) {
      this.application = await this.applicationService.editApplication({
        ...this.application,
        [interviewDate]: result.date,
      }).toPromise();
    } else {
      throw new Error('Cancelled');
    }
  }

  handleEditClick = async () => {
    const application = await this.applicationService.openEditModal(this.applicationId);

    if (application) {
      this.application = application;

      this.loadDependencies();
    }
  }

  handleUploadResume = async () => {
    const document = await this.documentService.openCreateModal(this.applicationId);
    await this.applicationService.editApplication({
      ...this.application,
      applicant: {
        ...this.application.applicant,
        resumeLink: document.id,
      },
    }).toPromise();
  }
}
