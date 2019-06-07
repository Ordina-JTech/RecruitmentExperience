import { ApplicationStates } from './application-states.enum';
import { BUM } from './bum';
import { Note } from './note';
import { Applicant } from './applicant';

export interface Application {
  id: number;
  applicant: Applicant;
  state: ApplicationStates;
  title: string;
  firstInterviewDate?: Date;
  secondInterviewDate?: Date;
  bum?: BUM;
  region?: string;
  department: string;
  bu?: string;
  notes: Note[];
  motivationLetter?: string;
}
