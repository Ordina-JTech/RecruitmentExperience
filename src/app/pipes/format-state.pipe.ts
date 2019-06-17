import { Pipe, PipeTransform } from '@angular/core';
import { ApplicationState, StateDescriptions } from '../definitions/application-states.enum';

@Pipe({
  name: 'formatState'
})
export class FormatStatePipe implements PipeTransform {

  transform(value: ApplicationState, args?: any): any {
    return StateDescriptions[value];
  }

}
