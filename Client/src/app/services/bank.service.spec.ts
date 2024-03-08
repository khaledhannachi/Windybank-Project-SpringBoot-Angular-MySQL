import { TestBed } from '@angular/core/testing';

import { BusinessService } from './bank.service';

describe('BusinessService', () => {
  let service: BusinessService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BusinessService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
