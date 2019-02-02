import { Directive, Input, ElementRef, OnChanges, SimpleChanges } from "@angular/core";

@Directive({
    selector: '[tagActive]'
})
export class TagActiveDirective implements OnChanges {
    @Input('tagActive') isActive = false;

    constructor(private el: ElementRef) {
        if (this.isActive) {
            this.el.nativeElement.style.backgroundColor = '#2db7f5';
            this.el.nativeElement.style.color ='#ffffff';
        }
    }

    ngOnChanges(changes: SimpleChanges) {
        console.log('Tag Active Directive:', this.isActive);
        if (this.isActive) {
            this.el.nativeElement.style.backgroundColor = '#2db7f5';
            this.el.nativeElement.style.color ='#ffffff';
        }
    }
}