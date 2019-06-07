import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ApplicationListComponent } from './application-list/application-list.component';
import { ApplicationStates } from './interfaces/application-states.enum';
import { ApplicationDetailComponent } from './application-detail/application-detail.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: `/applications/${ApplicationStates.NEW}`,
    pathMatch: 'full',
  },
  {
    component: ApplicationListComponent,
    path: 'applications/:state',
  },
  {
    component: ApplicationDetailComponent,
    path: 'applications/:state/:applicationId',
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
