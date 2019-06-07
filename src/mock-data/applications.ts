import { Application } from '../app/interfaces/application';
import { ApplicationStates } from '../app/interfaces/application-states.enum';

const APPLICATIONS: Application[] = [
  {
    id: 0,
    applicant: {
      prefix: 'Dhr', 
      firstName: 'Nils',
      lastName: 'van Eijk',
      email: 'nils.van.eijk@ordina.nl',
      phoneNumber: '0612345678',
      resume: 'test',
    },
    state: ApplicationStates.NEW,
    department: 'OSD',
    notes: [],
    title: 'Software Developer',
  },
  {
    id: 1,
    applicant: {
      prefix: 'Dhr',
      firstName: 'Nils',
      lastName: 'van Eijk',
      email: 'nils.van.eijk@ordina.nl',
      phoneNumber: '0612345678',
      resume: 'test',
    },
    state: ApplicationStates.ASSESSMENT,
    department: 'OSD',
    notes: [],
    bum: {
      prefix: 'Dhr. ',
      firstName: 'Piet',
      lastName: 'Snot',
      email: '',
      phoneNumber: '',
    },
    
    title: 'Software Developer',
  },
  {
    id: 2,
    applicant: {
      prefix: 'Dhr',
      firstName: 'Jan',
      lastName: 'Smit',
      email: 'nils.van.eijk@ordina.nl',
      phoneNumber: '0612345678',
      resume: 'test',
    },
    state: ApplicationStates.ASSESSMENT,
    department: 'OSD',
    notes: [],
    title: 'Software Developer',
  }
]

export default APPLICATIONS;