import { Component, OnInit } from '@angular/core';
import { ApplicationService } from '../services/application.service';
import { Application } from '../interfaces/application';
import { ActivatedRoute } from '@angular/router';
import { ApplicationStates } from '../interfaces/application-states.enum';
import { BusinessUnit } from '../interfaces/business-unit';
import { BusinessUnitManager } from '../interfaces/business-unit-manager';
import { BuService } from '../services/bu.service';
import { BumService } from '../services/bum.service';
import { Observable } from 'rxjs';
import { Region } from '../interfaces/region';
import { RegionService } from '../services/region.service';

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
              private currentRoute: ActivatedRoute) { }

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
    const states = Object.values(ApplicationStates);
    return (states.findIndex(state => state === this.application.state)) / (states.length - 1) * 100;
  }

  ngOnInit() {
    this.currentRoute.params.subscribe(params => this.applicationId = parseInt(params.applicationId));
  }

  async loadApplication() {
    this.application = await this.applicationService.getApplication(this.applicationId).toPromise();
    console.log(this.application)
    this.bu = this.buService.getBU(<number>this.application.businessUnit);
    this.bum = this.bumService.getBUM(<number>this.application.businessUnitManager)
    console.log(this.application.region)
    this.region = this.regionService.getRegion(<number>this.application.region);
  }
}
