import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookShelfComponent } from './book-shelf.component';

describe('BookShelfComponent', () => {
  let component: BookShelfComponent;
  let fixture: ComponentFixture<BookShelfComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookShelfComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookShelfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
