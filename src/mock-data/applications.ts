import { Application } from '../app/interfaces/application';
import { ApplicationStates } from '../app/interfaces/application-states.enum';
import BUMS from './bums';
import REGIONS from './regions';

const APPLICATIONS: Application[] = [
  {
    id: 0,
    applicant: {
      id: 0,
      prefix: 'van', 
      firstName: 'Nils',
      lastName: 'Eijk',
      email: 'nils.van.eijk@ordina.nl',
      phoneNumber: '0612345678',
      resumeLink: 'test',
    },
    businessUnitManagerId: 0,
    state: ApplicationStates.NEW,
    department: 'OSD',
    regionId: 0,
    notes: [],
    title: 'Software Developer',
  },
  {
    id: 1,
    applicant: {
      id: 1,
      prefix: 'Dhr',
      firstName: 'Nils',
      lastName: 'van Eijk 2',
      email: 'nils.van.eijk@ordina.nl',
      phoneNumber: '0612345678',
      resumeLink: 'test',
    },
    state: ApplicationStates.ASSESSMENT,
    department: 'OSD',
    regionId: 0,

    notes: [{
      id: 0,
      date: new Date(),
      author: 'Kevin',
      title: 'Eerste comment',
      text: 'Dit is een test',
    },
    {
      id: 1,
      date: new Date(),
      author: 'Kevin',
      title: 'Tweede comment',
      text: 'Dit is nog een test',
    },
    {
      id: 1,
      date: new Date(),
      author: 'Kevin',
      title: 'Derde comment',
      text: `Dit is nog een test
      few
      fe
      fw
      few`,
    },
  {
      id: 1,
      date: new Date(),
      author: 'Kevin',
      title: 'Vierde comment',
      text: 'Dit is nog een test',
    }],
    businessUnitManagerId: 2,
    title: 'Software Developer',
  },
  {
    id: 2,
    regionId: 1,

    applicant: {
      id: 3,
      prefix: 'Dhr',
      firstName: 'Jan',
      lastName: 'Smit',
      email: 'nils.van.eijk@ordina.nl',
      phoneNumber: '0612345678',
      resumeLink: 'test',
    },
    businessUnitManagerId: 1,
    state: ApplicationStates.ASSESSMENT,
    department: 'OSD',
    notes: [],
    title: 'Software Developer',
  }
]

export default APPLICATIONS;