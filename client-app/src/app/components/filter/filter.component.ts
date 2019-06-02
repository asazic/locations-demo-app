import { Component, EventEmitter, Output, Input } from '@angular/core';
import Filter from 'src/app/models/filter.model';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent {
    @Input() filter: Filter;
    @Output() filterChanged: EventEmitter<Filter> = new EventEmitter();

  constructor() { }

  public searchClicked(): void {
      this.filterChanged.emit(this.filter);
  }

}
