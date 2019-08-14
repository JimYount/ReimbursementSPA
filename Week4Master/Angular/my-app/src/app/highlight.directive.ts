import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: '[appHighlight]'
})
export class HighlightDirective {
  currColor: string;

  constructor(private el: ElementRef) { }

  private highlight(color: string) {
    this.el.nativeElement.style.backgroundColor = color;
  }
  @HostListener('mouseleave')
  private unhighlight() {
    this.highlight(this.currColor);
  }
  @HostListener('mouseenter')
  private bob() {
    this.currColor = this.el.nativeElement.style.backgroundColor;
    this.highlight('yellow');
  }
}
