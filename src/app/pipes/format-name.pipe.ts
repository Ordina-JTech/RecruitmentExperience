import { Pipe, PipeTransform } from '@angular/core';
import { Applicant } from '../definitions/applicant';
import { Person } from '../definitions/person';

@Pipe({
  name: 'formatName'
})
export class FormatNamePipe implements PipeTransform {

  transform(value?: Person, args?: any): any {
    return value ? `${value.firstName} ${value.prefix ? value.prefix + ' ' : ''}${value.lastName}` : '';
  }
}
