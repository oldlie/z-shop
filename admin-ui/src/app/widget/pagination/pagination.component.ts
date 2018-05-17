import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent implements OnInit {

  @Input()
  pages: number;
  @Input()
  page: number;
  @Input()
  cells = 7;
  @Output()
  pageIndexChangeEvent = new EventEmitter<number>();
  config: PaginationConfig;

  constructor() { }

  ngOnInit() {
    console.log('pages', this.pages);
    this.config = this.calculatePaginationInfo(this.page, this.pages, this.cells);
  }

  pageIndex(page: number) {
    this.page = page;
    this.pageIndexChangeEvent.emit(this.page);
  }

  goPrevious() {
    if (this.page > 0) {
      this.page--;
    }
    this.pageIndexChangeEvent.emit(this.page);
  }

  goNext() {
    if (this.page < this.pages - 1) {
      this.page++;
    }
    this.pageIndexChangeEvent.emit(this.page);
  }

  calculatePaginationInfo(index: number, pages: number,
    cellSize: number): PaginationConfig {

    const config = {} as PaginationConfig;

    let start = 0;
    let end = 0;
    // 如果总页数小于要显示格子数
    if (pages < cellSize) {
      config.prevOmit = false;
      config.nextOmit = false;
      start = 0;
      end = pages;
    } else {
      // 总页数大于要显示的格子数
      const middle = cellSize / 2;
      if (index - middle <= 0) {
        config.prevOmit = false;
        start = 0;
        if (index + middle <= pages) {
          config.nextOmit = true;
          end = index + middle;
        } else {
          config.nextOmit = false;
          end = pages;
        }
      } else {
        start = index - 2;
        if (index + middle <= pages) {
          config.prevOmit = true;
          config.nextOmit = true;
          end = index + middle;
        } else {
          config.prevOmit = true;
          config.nextOmit = false;
          start = pages - cellSize;
          end = pages;
        }
      }
    }

    config.list = this.buildList(start, end);
    config.index = index;
    return config;
  }

  private buildList(start: number, end: number): number[] {
    const list = [] as number[];
    for (let i = start; i < end; i++) {
      list.push(i);
    }
    return list;
  }
}

export interface PaginationConfig {
  index: number;
  list: number[];
  prevOmit: boolean;
  nextOmit: boolean;
}
