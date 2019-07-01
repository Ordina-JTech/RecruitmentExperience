import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BumSelectorComponent } from './bum-selector.component';

describe('BumSelectorComponent', () => {
  let component: BumSelectorComponent;
  let fixture: ComponentFixture<BumSelectorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BumSelectorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BumSelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
