import {ApplicationState} from './application-states.enum';

export interface ApplicationCounts {
  [ApplicationState.ASSESSMENT]: number;
  [ApplicationState.CONTRACT]: number;
  [ApplicationState.FIRST_INTERVIEW]: number;
  [ApplicationState.INVITED]: number;
  [ApplicationState.NEW]: number;
  [ApplicationState.OUTLINE]: number;
  [ApplicationState.SECOND_INTERVIEW]: number;
}
