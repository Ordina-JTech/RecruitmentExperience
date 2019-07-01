import { Component, OnInit, Input } from '@angular/core';
import { Application } from '../definitions/application';
import { Note } from '../definitions/note';
import { NoteService } from '../services/note.service';
// import * as orderBy from 'lodash/orderBy';

@Component({
  selector: 'app-application-notes',
  templateUrl: './application-notes.component.html',
  styleUrls: ['./application-notes.component.scss']
})
export class ApplicationNotesComponent implements OnInit {

  constructor(private noteService: NoteService) { }

  notes: Note[] = null;
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
    const notes = await this.noteService.getApplicationNotes(this.application.id).toPromise();
    // this.notes = orderBy(notes, ({creationDate}) => new Date(creationDate), 'desc');
    this.notes = notes;
  }

  handleEditClick = async (note: Note) => {
    await this.noteService.openEditModal(this.application.id, note);
    this.loadNotes();
  }

  handleCreateClick = async () => {
    await this.noteService.openCreateModal(this.application.id);
    this.loadNotes();
  }
}
