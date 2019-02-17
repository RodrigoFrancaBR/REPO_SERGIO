import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UnidadeGridComponent } from './unidade-grid.component';

describe('UnidadeGridComponent', () => {
  let component: UnidadeGridComponent;
  let fixture: ComponentFixture<UnidadeGridComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UnidadeGridComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UnidadeGridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
