import APPLICATIONS from './applications';
import { ApplicationStates } from 'src/app/interfaces/application-states.enum';
import BUMS from './bums';
import REGIONS from './regions';

const resolvers = {
  'applications': APPLICATIONS,
  'applications?state=new': APPLICATIONS.filter(application => application.state === ApplicationStates.NEW),
  'applications?state=contract': APPLICATIONS.filter(application => application.state === ApplicationStates.CONTRACT),
  'applications?state=assessment': APPLICATIONS.filter(application => application.state === ApplicationStates.ASSESSMENT),
  'applications/0': APPLICATIONS[0],
  'applications/1': APPLICATIONS[1],
  'applications/2': APPLICATIONS[2],
  'bums': BUMS,
  'bums/0': BUMS[0],
  'bums/1': BUMS[1],
  'bums/2': BUMS[2],
  'regions': REGIONS,
  'regions/0': REGIONS[0],
  'regions/1': REGIONS[1],
  'regions/2': REGIONS[2],
}

function resolveMockData<T>(path: string): T {
  return resolvers[path];
}

export default resolveMockData;