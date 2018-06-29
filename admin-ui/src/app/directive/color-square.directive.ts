import { Directive, Input, ElementRef } from "@angular/core";

@Directive({
    selector: '[colorSquare]'
})
export class ColorSquareDirective {

    @Input('colorSquare') color: string;

    constructor(private el: ElementRef) {
        this.el.nativeElement.style.borderColor = this.color;
    }
}
