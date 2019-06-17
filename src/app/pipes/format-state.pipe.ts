import { Pipe, PipeTransform } from '@angular/core';
import { ApplicationState, STATE_DESCRIPTIONS } from '../definitions/application-states.enum';

@Pipe({
  name: 'formatState'
})
export class FormatStatePipe implements PipeTransform {

  transform(value: ApplicationState, args?: any): any {
    return STATE_DESCRIPTIONS[value];
  }

}
