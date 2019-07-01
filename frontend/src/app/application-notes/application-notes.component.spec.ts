import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplicationNotesComponent } from './application-notes.component';

describe('ApplicationNotesComponent', () => {
  let component: ApplicationNotesComponent;
  let fixture: ComponentFixture<ApplicationNotesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ApplicationNotesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApplicationNotesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
