import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';
import { EditDialogComponent } from '../dialogs/edit-dialog/edit-dialog.component';
import { FieldType, EditDialog } from '../definitions/edit-dialog';
import { Document } from '../interfaces/document';

@Injectable({
  providedIn: 'root'
})
export class DocumentService {

  constructor(private api: ApiService,
              private dialog: MatDialog) { }

  getApplicationDocuments(applicationId: number): Observable<Document[]> {
    return this.api.get(`applications/${applicationId}/documents`);
  }

  createApplicationDocument(applicationId: number, document: Document): Observable<Document> {
    return this.api.upload(`applications/${applicationId}/documents`, document, document.file);
  }

  editApplicationDocument(applicationId: number, document: Document): Observable<Document> {
    return this.api.put(`applications/${applicationId}/documents/${document.uuid}`, document);
  }

  async openCreateModal(applicationId: number): Promise<Document> {
    const document = await this.openModal({
      uuid: '',
      author: '',
      extension: '',
      title: '',
      creationDate: new Date(),
    } as Document);

    if (document) {
      return this.createApplicationDocument(applicationId, document).toPromise();
    } else {
      return null;
    }
  }

  async openEditModal(applicationId: number, document: Document): Promise<Document> {
    const updatedDocument = await this.openModal(document);

    if (updatedDocument) {
      return this.editApplicationDocument(applicationId, updatedDocument).toPromise();
    }
  }

  private async openModal(document: Document): Promise<Document> {
    const dialogRef = this.dialog.open(EditDialogComponent, {
      width: '500px',
      data: {
        title: {
          type: FieldType.Text,
          label: 'Titel',
          initialValue: document.title
        },
        author: {
          type: FieldType.Text,
          label: 'Auteur',
          initialValue: document.author
        },
        file: {
          type: FieldType.File,
          label: 'Bestand',
        }
      } as EditDialog
    });

    const dialogResult = await dialogRef.afterClosed().toPromise();

    if (dialogResult) {
      return {
        ...document,
        ...dialogResult,
      } as Document;
    } else {
      return null;
    }
  }
}
