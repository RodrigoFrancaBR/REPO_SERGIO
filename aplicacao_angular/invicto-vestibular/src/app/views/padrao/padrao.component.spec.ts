import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PadraoComponent } from './padrao.component';

describe('PadraoComponent', () => {
  let component: PadraoComponent;
  let fixture: ComponentFixture<PadraoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PadraoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PadraoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
