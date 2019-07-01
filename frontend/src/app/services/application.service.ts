import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {all} from 'bluebird';
import { Application } from '../definitions/application';
import { ApiService } from './api.service';
import { ApplicationState } from '../definitions/application-states.enum';
import { ApplicationCounts } from '../definitions/application-counts';
import { RegionService } from './region.service';
import { DepartmentService } from './department.service';
import { MatDialog } from '@angular/material';
import { EditDialogComponent } from '../dialogs/edit-dialog/edit-dialog.component';
import { EditDialog, FieldType, Option } from '../definitions/edit-dialog';
import { BumService } from './bum.service';
import { BuService } from './bu.service';

@Injectable({
  providedIn: 'root'
})
export class ApplicationService {

  constructor(private api: ApiService,
              private regionService: RegionService,
              private departmentService: DepartmentService,
              private bumService: BumService,
              private buService: BuService,
              private dialog: MatDialog) { }

  getApplicationCounts(): Observable<ApplicationCounts> {
    return this.api.get(`application-states`);
  }

  getApplications(state: string): Observable<Application[]> {
    return this.api.get<Application[]>(`applications?state=${state}`);
  }

  getApplication(applicationId: number): Observable<Application> {
    return this.api.get<Application>(`applications/${applicationId}`);
  }

  createApplication(application: Application): Observable<Application> {
    return this.api.post(`applications`, application);
  }

  editApplication(application: Application): Observable<Application> {
    return this.api.put(`applications/${application.id}`, application);
  }

  promoteApplication(application: Application): Observable<Application> {
    return this.api.post(`applications/${application.id}/promote`, {});
  }

  async openEditModal(applicationId: number): Promise<Application> {
    const application = await this.openModal(await this.getApplication(applicationId).toPromise());

    if (application) {
      return this.editApplication(application).toPromise();
    }
  }

  async openCreateModal(): Promise<Application> {
    const application = await this.openModal({
      id: 0,
      applicant: {
        id: 0,
        firstName: '',
        prefix: '',
        lastName: '',
        resumeLink: '',
        phoneNumber: '',
        email: '',
      },
      businessUnitId: 0,
      businessUnitManagerId: 0,
      notes: [],
      state: ApplicationState.NEW,
      title: '',
      departmentId: 0,
    });

    if (application) {
      return this.createApplication(application).toPromise();
    } else {
      return null;
    }
  }

  private async openModal(application: Application): Promise<Application> {
    const [regions, departments, bums, bus] = await all([
      this.regionService.getRegions().toPromise(),
      this.departmentService.getDepartments().toPromise(),
      this.bumService.getBUMs().toPromise(),
      this.buService.getBUs().toPromise(),
    ]);

    const dialogRef = this.dialog.open(EditDialogComponent, {
      width: '500px',
      data: {
        firstName: {type: FieldType.Text, label: 'Voornaam', initialValue: application.applicant.firstName},
        prefix: {type: FieldType.Text, label: 'Tussenvoegsel', initialValue: application.applicant.prefix},
        lastName: {type: FieldType.Text, label: 'Achternaam', initialValue: application.applicant.lastName},
        phoneNumber: {type: FieldType.Text, label: 'Telefoonnummer', initialValue: application.applicant.phoneNumber},
        email: {type: FieldType.Text, label: 'Emailadres', initialValue: application.applicant.email},
        title: {type: FieldType.Text, label: 'Functie', initialValue: application.title},
        regionId: {
          type: FieldType.Select,
          label: 'Regio',
          options: regions.map(region => ({name: region.id, value: region.name}) as Option),
          initialValue: application.regionId,
        },
        departmentId: {
          type: FieldType.Select,
          label: 'Afdeling',
          options: departments.map(department => ({name: department.id, value: department.name}) as Option),
          initialValue: application.regionId,
        },
        businessUnitManagerId: {
          type: FieldType.Select,
          label: 'BUM',
          options: bums.map(bum => ({name: bum.id, value: bum.firstName}) as Option),
          initialValue: application.businessUnitManagerId,
        },
        businessUnitId: {
          type: FieldType.Select,
          label: 'Business Unit',
          options: bus.map(bu => ({name: bu.id, value: bu.name}) as Option),
          initialValue: application.businessUnitId,
        },
      }  as EditDialog,
    });

    const dialogResult = await dialogRef.afterClosed().toPromise();

    if (dialogResult) {
      const {
        firstName,
        prefix,
        lastName,
        email,
        phoneNumber,
        ...rest
      } = dialogResult;

      return {
        ...application,
        ...rest,
        applicant: {
          ...application.applicant,
          firstName,
          prefix,
          lastName,
          email,
          phoneNumber,
        },
      };
    } else {
      return null;
    }
  }
}
