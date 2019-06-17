import { ApplicationState } from './application-states.enum';
import { BusinessUnitManager } from './business-unit-manager';
import { Note } from './note';
import { Applicant } from './applicant';
import { BusinessUnit } from './business-unit';
import { Region } from './region';

export interface Application {
  id: number;
  applicant: Applicant;
  businessUnitId?: number;
  businessUnitManagerId?: number;
  notes: Note[];
  regionId?: number;
  state: ApplicationState;
  motivationLetterLink?: string;
  firstInterviewDateTime?: Date;
  secondInterviewDateTime?: Date;
  title: string;
  departmentId: number;
}
