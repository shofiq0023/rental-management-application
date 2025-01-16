import { TestBed } from '@angular/core/testing';

import { BlogsApiService } from './blogs-api.service';

describe('BlogsApiService', () => {
  let service: BlogsApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BlogsApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
