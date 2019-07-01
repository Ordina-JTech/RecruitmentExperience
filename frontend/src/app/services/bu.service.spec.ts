import { TestBed } from '@angular/core/testing';

import { BuService } from './bu.service';

describe('BuService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BuService = TestBed.get(BuService);
    expect(service).toBeTruthy();
  });
});
