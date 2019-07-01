import { TestBed } from '@angular/core/testing';

import { BumService } from './bum.service';

describe('BumService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BumService = TestBed.get(BumService);
    expect(service).toBeTruthy();
  });
});
