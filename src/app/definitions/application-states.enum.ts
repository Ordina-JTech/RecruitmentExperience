export enum ApplicationState {
  NEW = 'NEW',
  INVITED = 'INVITED',
  FIRST_INTERVIEW = 'FIRST_INTERVIEW',
  SECOND_INTERVIEW = 'SECOND_INTERVIEW',
  ASSESSMENT = 'ASSESSMENT',
  OUTLINE = 'OUTLINE',
  CONTRACT = 'CONTRACT',
}

export const StateDescriptions = {
  [ApplicationState.NEW]: 'Nieuwe sollicitatie',
  [ApplicationState.INVITED]: 'Uitgenodigd',
  [ApplicationState.FIRST_INTERVIEW]: 'Eerste gesprek',
  [ApplicationState.SECOND_INTERVIEW]: 'Tweede gesprek',
  [ApplicationState.ASSESSMENT]: 'Assessment',
  [ApplicationState.OUTLINE]: 'Outline',
  [ApplicationState.CONTRACT]: 'Contract',
}
