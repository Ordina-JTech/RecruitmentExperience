import { Pipe, PipeTransform } from '@angular/core';
import dateFormat from 'dateformat';

dateFormat.i18n = {
  dayNames: [
      'Zo', 'Ma', 'Di', 'Wo', 'Do', 'Vrij', 'Za',
      'Zondag', 'Manadag', 'Dinsdag', 'Woensdag', 'Donderdag', 'Vrijdag', 'Zaterdag'
  ],
  monthNames: [
      'Jan', 'Feb', 'Mar', 'Apr', 'Mei', 'Jun', 'Jul', 'Aug', 'Sep', 'Okt', 'Nov', 'Dec',
      'Januari', 'Februari', 'Maart', 'April', 'Mei', 'Juni', 'Juli', 'Augustus', 'September', 'Oktober', 'November', 'December'
  ],
  timeNames: [
      'a', 'p', 'am', 'pm', 'A', 'P', 'AM', 'PM'
  ]
};

@Pipe({
  name: 'formatDate'
})
export class FormatDatePipe implements PipeTransform {

  transform(value: string | Date, args?: any): any {
    const date = typeof value === 'string' ? new Date(value) : value;
    return dateFormat(date, 'dddd, mmmm dS, yyyy');
  }
}
