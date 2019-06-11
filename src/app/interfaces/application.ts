import { ApplicationStates } from './application-states.enum';
import { BusinessUnitManager } from './business-unit-manager';
import { Note } from './note';
import { Applicant } from './applicant';
import { BusinessUnit } from './business-unit';
import { Region } from './region';

export interface Application {
  id: number;
  applicant: number | Applicant;
  businessUnit?: number | BusinessUnit;
  businessUnitManager?: number | BusinessUnitManager;
  notes: Note[];
  region?: number | Region;
  state: ApplicationStates;
  motivationLetterLink?: string;
  firstInterviewDateTime?: Date;
  secondInterviewDateTime?: Date;
  title: string;
  department: string;
}
