import { Component, OnInit, Input } from '@angular/core';
import { Application } from '../definitions/application';
import { DocumentService } from '../services/document.service';
import { Document } from '../interfaces/document';

@Component({
  selector: 'app-application-documents',
  templateUrl: './application-documents.component.html',
  styleUrls: ['./application-documents.component.scss']
})
export class ApplicationDocumentsComponent implements OnInit {

  constructor(private documentService: DocumentService) { }

  documents: Document[] = null;
  private _application: Application;

  ngOnInit() {
  }

  public get application(): Application {
    return this._application;
  }

  @Input()
  public set application(value: Application) {
    this._application = value;
    this.loadDocuments();
  }

  async loadDocuments() {
    const documents = await this.documentService.getApplicationDocuments(this.application.id).toPromise();
    this.documents = documents;
  }

  handleCreateClick = async () => {
    await this.documentService.openCreateModal(this.application.id);
    this.loadDocuments();
  }

  handleDownloadClick = async (document: Document) => {
    const blob = await this.documentService.downloadDocument(this.application.id, document).toPromise();
    this.saveBlob(blob, `${document.title}.${document.extension}`);
  }

  saveBlob(blob: Blob, fileName: string) {
    const a = document.createElement('a');
    document.body.appendChild(a);
    const url = window.URL.createObjectURL(blob);
    a.href = url;
    a.download = fileName;
    a.click();
    window.URL.revokeObjectURL(url);
  }
}
