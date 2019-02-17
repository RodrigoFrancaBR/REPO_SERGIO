import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UnidadeRaizComponent } from './unidade-raiz.component';

describe('UnidadeRaizComponent', () => {
  let component: UnidadeRaizComponent;
  let fixture: ComponentFixture<UnidadeRaizComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UnidadeRaizComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UnidadeRaizComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
