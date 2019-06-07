import { Pipe, PipeTransform } from '@angular/core';
import { ApplicationStates, StateDescriptions } from '../interfaces/application-states.enum';

@Pipe({
  name: 'formatState'
})
export class FormatStatePipe implements PipeTransform {

  transform(value: ApplicationStates, args?: any): any {
    return StateDescriptions[value];
  }

}
