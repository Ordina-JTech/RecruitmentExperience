import { Person } from './person';
import { BusinessUnit } from './business-unit';

export interface BusinessUnitManager extends Person {
  id: number;
  businessUnit: BusinessUnit;
}
