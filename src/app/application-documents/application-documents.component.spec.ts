import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplicationDocumentsComponent } from './application-documents.component';

describe('ApplicationDocumentsComponent', () => {
  let component: ApplicationDocumentsComponent;
  let fixture: ComponentFixture<ApplicationDocumentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ApplicationDocumentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApplicationDocumentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
