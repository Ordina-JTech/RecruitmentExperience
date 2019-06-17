import { Component, OnInit } from '@angular/core';
import { ApplicationState } from '../definitions/application-states.enum';
import { ApplicationCounts } from '../definitions/application-counts';
import { Observable } from 'rxjs';
import { ApplicationService } from '../services/application.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-side-menu',
  templateUrl: './side-menu.component.html',
  styleUrls: ['./side-menu.component.scss']
})
export class SideMenuComponent implements OnInit {

  constructor(private applicationService: ApplicationService) { }

  urls = Object.values(ApplicationState);
  applicationCounts: ApplicationCounts;
  currentPage: string;

  ngOnInit() {
    this.loadApplicationCounts();
  }

  selectRoute(url: string) {
    this.currentPage = url;
  }

  async loadApplicationCounts() {
    this.applicationCounts = await this.applicationService.getApplicationCounts().toPromise();
  }

}
