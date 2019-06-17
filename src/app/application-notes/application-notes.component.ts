import { Component, OnInit, Input } from '@angular/core';
import { Application } from '../definitions/application';
import { ApplicationService } from '../services/application.service';
import { Observable } from 'rxjs';
import { Note } from '../definitions/note';

@Component({
  selector: 'app-application-notes',
  templateUrl: './application-notes.component.html',
  styleUrls: ['./application-notes.component.scss']
})
export class ApplicationNotesComponent implements OnInit {

  constructor(private applicationService: ApplicationService) { }

  notes: Observable<Note[]> = null;
  private _application: Application;

  ngOnInit() {
  }

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
