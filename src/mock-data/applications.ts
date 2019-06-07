import { Application } from '../app/interfaces/application';
import { ApplicationStates } from '../app/interfaces/application-states.enum';
import BUMS from './bums';

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
    bum: BUMS[0],
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
      lastName: 'van Eijk 2',
      email: 'nils.van.eijk@ordina.nl',
      phoneNumber: '0612345678',
      resume: 'test',
    },
    state: ApplicationStates.ASSESSMENT,
    department: 'OSD',
    notes: [{
      id: 0,
      date: new Date(),
      text: 'Dit is een test',
    },
    {
      id: 1,
      date: new Date(),
      text: 'Dit is nog een test',
    },
    {
      id: 1,
      date: new Date(),
      text: `Dit is nog een test
      few
      fe
      fw
      few`,
    },
  {
      id: 1,
      date: new Date(),
      text: 'Dit is nog een test',
    }],
    bum: BUMS[1],
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
    bum: BUMS[2],
    state: ApplicationStates.ASSESSMENT,
    department: 'OSD',
    notes: [],
    title: 'Software Developer',
  }
]

export default APPLICATIONS;