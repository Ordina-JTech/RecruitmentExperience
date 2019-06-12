import { Component, OnInit } from '@angular/core';
import { ApplicationStates } from '../interfaces/application-states.enum';
import { ApplicationCounts } from '../interfaces/application-counts';
import { Observable } from 'rxjs';
import { ApplicationService } from '../services/application.service';

@Component({
  selector: 'app-side-menu',
  templateUrl: './side-menu.component.html',
  styleUrls: ['./side-menu.component.scss']
})
export class SideMenuComponent implements OnInit {

  constructor(private applicationService: ApplicationService) { }

  urls = Object.values(ApplicationStates);
  applicationCounts: ApplicationCounts;

  ngOnInit() {
    this.loadApplicationCounts();
  }

  async loadApplicationCounts() {
    this.applicationCounts = await this.applicationService.getApplicationCounts().toPromise();
    console.log(this.applicationCounts)
  }

}
