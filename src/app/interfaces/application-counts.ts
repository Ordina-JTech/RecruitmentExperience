import {ApplicationStates} from './application-states.enum';

export interface ApplicationCounts {
  [ApplicationStates.ASSESSMENT]: number;
  [ApplicationStates.CONTRACT]: number;
  [ApplicationStates.FIRST_INTERVIEW]: number;
  [ApplicationStates.INVITED]: number;
  [ApplicationStates.NEW]: number;
  [ApplicationStates.OUTLINE]: number;
  [ApplicationStates.SECOND_INTERVIEW]: number;
}
