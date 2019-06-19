import { Component, OnInit, OnDestroy } from '@angular/core';
import { ApplicationState } from '../definitions/application-states.enum';
import { ApplicationCounts } from '../definitions/application-counts';
import { Subscription } from 'rxjs';
import { ApplicationService } from '../services/application.service';
import { MessagingService } from '../services/messaging.service';

@Component({
  selector: 'app-side-menu',
  templateUrl: './side-menu.component.html',
  styleUrls: ['./side-menu.component.scss']
})
export class SideMenuComponent implements OnInit, OnDestroy {

  constructor(private applicationService: ApplicationService,
              private messagingService: MessagingService) { }

  urls = Object.values(ApplicationState);
  applicationCounts: ApplicationCounts;
  currentPage: string;
  messageSubscription: Subscription;


  ngOnInit() {
    this.loadApplicationCounts();

    this.messageSubscription = this.messagingService.ofType('RELOAD_APPLICATION_COUNTS').subscribe(this.loadApplicationCounts);
  }

  ngOnDestroy() {
    this.messageSubscription.unsubscribe();
  }

  selectRoute(url: string) {
    this.currentPage = url;
  }

  loadApplicationCounts = async () => {
    this.applicationCounts = await this.applicationService.getApplicationCounts().toPromise();
  }

}
