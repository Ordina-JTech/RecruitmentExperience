import { Component, OnInit, Input } from '@angular/core';
import { Application } from '../interfaces/application';
import { ApplicationService } from '../services/application.service';
import { Observable } from 'rxjs';
import { Note } from '../interfaces/note';

@Component({
  selector: 'app-application-notes',
  templateUrl: './application-notes.component.html',
  styleUrls: ['./application-notes.component.scss']
})
export class ApplicationNotesComponent implements OnInit {

  constructor(private applicationService: ApplicationService) { }

  notes: Observable<Note[]> = null;

  ngOnInit() {
  }

  private _application: Application;
  public get application(): Application {
    return this._application;
  }

  @Input()
  public set application(value: Application) {
    this._application = value;
    this.loadNotes();
  }

  async loadNotes() {
    this.notes = this.applicationService.getApplicationNotes(this.application.id);
  }
}
