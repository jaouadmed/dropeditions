import { TestBed } from '@angular/core/testing';

import { MarketstackService } from './marketstack.service';

describe('MarketstackService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MarketstackService = TestBed.get(MarketstackService);
    expect(service).toBeTruthy();
  });
});
