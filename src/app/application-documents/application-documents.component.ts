import { Component, OnInit, Input } from '@angular/core';
import { Application } from '../definitions/application';
import { DocumentService } from '../services/document.service';

@Component({
  selector: 'app-application-documents',
  templateUrl: './application-documents.component.html',
  styleUrls: ['./application-documents.component.scss']
})
export class ApplicationDocumentsComponent implements OnInit {

  constructor(private documentService: DocumentService) { }

  @Input()
  application!: Application;

  ngOnInit() {
  }

  handleCreateClick = async () => {
    await this.documentService.openCreateModal(this.application.id);
    // this.loadDocu();
  }
}
