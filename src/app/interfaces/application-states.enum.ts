export enum ApplicationStates {
  NEW = 'new',
  INVITED = 'invited',
  FIRST_INTERVIEW = 'first_interview',
  SECOND_INTERVIEW = 'second_interview',
  ASSESSMENT = 'assessment',
  OUTLINE = 'outline',
  CONTRACT = 'contract',
}

export const StateDescriptions = {
  [ApplicationStates.NEW]: 'Nieuwe sollicitatie',
  [ApplicationStates.INVITED]: 'Uitgenodigd voor gesprek',
  [ApplicationStates.FIRST_INTERVIEW]: 'Eerste gesprek',
  [ApplicationStates.SECOND_INTERVIEW]: 'Tweede gesprek',
  [ApplicationStates.ASSESSMENT]: 'Assessment',
  [ApplicationStates.OUTLINE]: 'Outline',
  [ApplicationStates.CONTRACT]: 'Contract',
}
