import { Component, OnInit } from '@angular/core';
import { ApplicationStates } from '../interfaces/application-states.enum';
import { ApplicationCounts } from '../interfaces/application-counts';
import { Observable } from 'rxjs';
import { ApplicationService } from '../services/application.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-side-menu',
  templateUrl: './side-menu.component.html',
  styleUrls: ['./side-menu.component.scss']
})
export class SideMenuComponent implements OnInit {

  constructor(private applicationService: ApplicationService,
              private activatedRoute: ActivatedRoute) { }

  urls = Object.values(ApplicationStates);
  applicationCounts: ApplicationCounts;
  currentPage: string;

  ngOnInit() {
    this.loadApplicationCounts();

    this.activatedRoute.params.subscribe(params => {
      this.currentPage = params.state;
      console.log(this.currentPage)
    });
  }

  selectRoute(url) {
    this.activatedRoute = url; 
    console.log(this.activatedRoute);
  }

  async loadApplicationCounts() {
    this.applicationCounts = await this.applicationService.getApplicationCounts().toPromise();
    console.log(this.applicationCounts)
  }

}
