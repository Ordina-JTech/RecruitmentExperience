import { ApplicationStates } from './application-states.enum';
import { BusinessUnitManager } from './business-unit-manager';
import { Note } from './note';
import { Applicant } from './applicant';
import { BusinessUnit } from './business-unit';
import { Region } from './region';

export interface Application {
  id: number;
  applicant: number | Applicant;
  businessUnitId?: number;
  businessUnitManagerId?: number;
  notes: Note[];
  regionId?: number;
  state: ApplicationStates;
  motivationLetterLink?: string;
  firstInterviewDateTime?: Date;
  secondInterviewDateTime?: Date;
  title: string;
  department: string;
}
