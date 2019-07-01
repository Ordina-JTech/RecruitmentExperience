import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Injectable, ErrorHandler } from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {
  MatButtonModule,
  MatSidenavModule,
  MatListModule,
  MatToolbarModule,
  MatIconModule,
  MatTableModule,
  MatCardModule,
  MatProgressBarModule,
  MatTabsModule,
  MatSelectModule,
  MatFormFieldModule,
  MatInputModule,
  MatDialogModule
} from '@angular/material';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TopMenuComponent } from './top-menu/top-menu.component';
import { SideMenuComponent } from './side-menu/side-menu.component';
import { ApplicationListComponent } from './application-list/application-list.component';
import { FormatNamePipe } from './pipes/format-name.pipe';
import { ApplicationDetailComponent } from './application-detail/application-detail.component';
import { FormatStatePipe } from './pipes/format-state.pipe';
import { BumSelectorComponent } from './bum-selector/bum-selector.component';
import { ApplicationDocumentsComponent } from './application-documents/application-documents.component';
import { ApplicationNotesComponent } from './application-notes/application-notes.component';
import { FormatDatePipe } from './pipes/format-date.pipe';
import { HttpClientModule } from '@angular/common/http';
import { EditDialogComponent } from './dialogs/edit-dialog/edit-dialog.component';
import { ReactiveFormsModule } from '@angular/forms';

// import * as Sentry from '@sentry/browser';

// Sentry.init({
//   dsn: 'https://d3aaf923065447198cc7b2965a6af84e@sentry.io/1484418',
// });

// @Injectable()
// export class SentryErrorHandler implements ErrorHandler {
//   constructor() {}
//   handleError(error) {
//     /*const eventId = */Sentry.captureException(error.originalError || error);
//     // Sentry.showReportDialog({ eventId });
//   }
// }
@NgModule({
  declarations: [
    AppComponent,
    TopMenuComponent,
    SideMenuComponent,
    ApplicationListComponent,
    FormatNamePipe,
    ApplicationDetailComponent,
    FormatStatePipe,
    BumSelectorComponent,
    ApplicationDocumentsComponent,
    ApplicationNotesComponent,
    FormatDatePipe,
    EditDialogComponent,
  ],
  imports: [
    MatButtonModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSidenavModule,
    MatListModule,
    MatToolbarModule,
    MatIconModule,
    MatTableModule,
    MatCardModule,
    MatProgressBarModule,
    MatTabsModule,
    MatSelectModule,
    MatFormFieldModule,
    MatInputModule,
    HttpClientModule,
    MatDialogModule,
    ReactiveFormsModule,
  ],
  // providers: [{provide: ErrorHandler, useClass: SentryErrorHandler}],
  bootstrap: [AppComponent],
  entryComponents: [
    EditDialogComponent,
  ]
})
export class AppModule { }
