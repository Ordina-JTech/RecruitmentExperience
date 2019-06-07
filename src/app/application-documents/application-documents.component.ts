import { Component, OnInit, Input } from '@angular/core';
import { Application } from '../interfaces/application';

@Component({
  selector: 'app-application-documents',
  templateUrl: './application-documents.component.html',
  styleUrls: ['./application-documents.component.scss']
})
export class ApplicationDocumentsComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  @Input()
  application!: Application;

}
