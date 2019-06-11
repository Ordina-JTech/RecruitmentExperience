import APPLICATIONS from './applications';
import { ApplicationStates } from 'src/app/interfaces/application-states.enum';

const resolvers = {
  'applications': APPLICATIONS,
  'applications?state=new': APPLICATIONS.filter(application => application.state === ApplicationStates.NEW),
  'applications?state=contract': APPLICATIONS.filter(application => application.state === ApplicationStates.CONTRACT),
  'applications?state=assessment': APPLICATIONS.filter(application => application.state === ApplicationStates.ASSESSMENT),
  'applications/0': APPLICATIONS[0],
  'applications/1': APPLICATIONS[1],
}

function resolveMockData<T>(path: string): T {
  return resolvers[path];
}

export default resolveMockData;