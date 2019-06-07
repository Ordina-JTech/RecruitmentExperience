import { Injectable } from '@angular/core';
import { BUM } from '../interfaces/bum';
import { mockPromise } from './helpers';
import BUMS from 'src/mock-data/bums';

@Injectable({
  providedIn: 'root'
})
export class BumService {

  constructor() { }

  getBUMs(): Promise<BUM[]> {
    return mockPromise(() => BUMS);
  }

  getBUM(id: number): Promise<BUM> {
    return mockPromise(() => BUMS.find(bum => bum.id === id));
  }
}
