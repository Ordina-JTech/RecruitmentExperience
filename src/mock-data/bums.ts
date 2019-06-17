import { BusinessUnitManager } from "src/app/definitions/business-unit-manager";
import BUS from './bus';
const BUMS: BusinessUnitManager[] = [
  {
    id: 0,
    prefix: 'Dhr',
    firstName: 'Kevin',
    lastName: 'Donkers',
    phoneNumber: '0612345678',
    email: 'kevin.donkers@ordina.nl',
    businessUnit: BUS[0],
  },
  {
    id: 1,
    prefix: 'Dhr',
    firstName: 'Henk',
    lastName: 'Dissel',
    phoneNumber: '0612345678',
    email: 'henk.dissel@ordina.nl',
    businessUnit: BUS[0],    
  },
  {
    id: 2,
    prefix: 'Dhr',
    firstName: 'Freek',
    lastName: 'Vonk',
    phoneNumber: '0612345678',
    email: 'freek.vonk@ordina.nl',
    businessUnit: BUS[0],
  }
]

export default BUMS;