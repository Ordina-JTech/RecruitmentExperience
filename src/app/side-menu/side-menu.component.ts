import { Component, OnInit } from '@angular/core';
import { ApplicationStates } from '../interfaces/application-states.enum';

@Component({
  selector: 'app-side-menu',
  templateUrl: './side-menu.component.html',
  styleUrls: ['./side-menu.component.scss']
})
export class SideMenuComponent implements OnInit {

  constructor() { }

  urls = Object.values(ApplicationStates);

  ngOnInit() {
  }

}
