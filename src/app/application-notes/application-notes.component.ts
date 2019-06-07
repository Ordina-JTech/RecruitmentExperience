import { Component, OnInit, Input } from '@angular/core';
import { Application } from '../interfaces/application';

@Component({
  selector: 'app-application-notes',
  templateUrl: './application-notes.component.html',
  styleUrls: ['./application-notes.component.scss']
})
export class ApplicationNotesComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  @Input()
  application: Application;
}
