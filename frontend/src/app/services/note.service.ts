import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Note } from '../definitions/note';
import { MatDialog } from '@angular/material';
import { EditDialogComponent } from '../dialogs/edit-dialog/edit-dialog.component';
import { FieldType, EditDialog } from '../definitions/edit-dialog';

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  constructor(private api: ApiService,
              private dialog: MatDialog) { }

  getApplicationNotes(applicationId: number): Observable<Note[]> {
    return this.api.get(`applications/${applicationId}/notes`);
  }

  createApplicationNote(applicationId: number, note: Note): Observable<Note> {
    return this.api.post(`applications/${applicationId}/notes`, note);
  }

  editApplicationNote(applicationId: number, note: Note): Observable<Note> {
    return this.api.put(`applications/${applicationId}/notes/${note.id}`, note);
  }

  async openCreateModal(applicationId: number): Promise<Note> {
    const note = await this.openModal({
      id: 0,
      text: '',
      title: '',
      author: '',
      creationDate: new Date(),
    } as Note);

    if (note) {
      return this.createApplicationNote(applicationId, note).toPromise();
    } else {
      return null;
    }
  }

  async openEditModal(applicationId: number, note: Note): Promise<Note> {
    const updatedNote = await this.openModal(note);

    if (updatedNote) {
      return this.editApplicationNote(applicationId, updatedNote).toPromise();
    }
  }

  private async openModal(note: Note): Promise<Note> {
    const dialogRef = this.dialog.open(EditDialogComponent, {
      width: '500px',
      data: {
        title: {
          type: FieldType.Text,
          label: 'Titel',
          initialValue: note.title
        },
        author: {
          type: FieldType.Text,
          label: 'Auteur',
          initialValue: note.author
        },
        text: {
          type: FieldType.RichText,
          label: 'Tekst',
          initialValue: note.text
        }
      } as EditDialog
    });

    const dialogResult = await dialogRef.afterClosed().toPromise();
    if (dialogResult) {
      return {
        ...note,
        ...dialogResult,
      } as Note;
    } else {
      return null;
    }
  }
}
