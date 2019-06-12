import { Pipe, PipeTransform } from '@angular/core';
import { Applicant } from '../interfaces/applicant';
import { Person } from '../interfaces/person';

@Pipe({
  name: 'formatName'
})
export class FormatNamePipe implements PipeTransform {

  transform(value?: Person, args?: any): any {
    return value ? `${value.firstName} ${value.lastName}` : '';
  }
}
