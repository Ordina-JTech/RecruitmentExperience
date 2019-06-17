import { Person } from './person';

export interface Applicant extends Person {
  id: number;
  resumeLink: string;
}
