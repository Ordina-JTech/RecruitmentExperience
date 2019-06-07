import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule, MatSidenavModule, MatListModule, MatToolbarModule, MatIconModule, MatTableModule, MatCardModule, MatProgressBarModule} from '@angular/material';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TopMenuComponent } from './top-menu/top-menu.component';
import { SideMenuComponent } from './side-menu/side-menu.component';
import { ApplicationListComponent } from './application-list/application-list.component';
import { ApplicationComponent } from './application/application.component';
import { FormatNamePipe } from './pipes/format-name.pipe';
import { ApplicationDetailComponent } from './application-detail/application-detail.component';
import { FormatStatePipe } from './pipes/format-state.pipe';

@NgModule({
  declarations: [
    AppComponent,
    TopMenuComponent,
    SideMenuComponent,
    ApplicationListComponent,
    ApplicationComponent,
    FormatNamePipe,
    ApplicationDetailComponent,
    FormatStatePipe
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
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
