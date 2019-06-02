import { Component, ViewChild, AfterViewInit } from '@angular/core';
import LocationModel from 'src/app/models/location.model';
import LocationsService from 'src/app/services/locations.service';
import { MatPaginator } from '@angular/material/paginator';
import { of as observableOf, merge } from 'rxjs';
import { catchError, map, startWith, switchMap } from 'rxjs/operators';
import { MatDialog } from '@angular/material';
import { NewLocationComponent } from '../new-location/new-location.component';
import Filter from 'src/app/models/filter.model';

@Component({
    selector: 'app-location-list',
    templateUrl: './location-list.component.html',
    styleUrls: ['./location-list.component.css']
})
export class LocationListComponent implements AfterViewInit {
    displayedColumns: string[] = ['id', 'location name', 'lat', 'lng', 'new'];
    locations: LocationModel[] = [];
    filter: Filter = new Filter;

    isLoadingResults = true;
    isRateLimitReached = false;
    @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;

    constructor(private locationsService: LocationsService, private dialog: MatDialog) { }

    ngAfterViewInit() {
        this.refreshList();
    }

    public addNew(): void {
        const dialogRef = this.dialog.open(NewLocationComponent, {
            width: '250px'
        });

        dialogRef.afterClosed().subscribe(result => {
            // Add to database
            if(!result) {
                return;
            }
            this.locationsService.create(result).subscribe(() => {
                this.refreshList();
            });
        });
    }

    public onFilterChange(): void {
        this.refreshList();
    }

    private refreshList(): void {
        this.isLoadingResults = true;
        if (this.filter.addressId) {
            this.locationsService.getFiltered(this.filter).subscribe((data) => {
                this.locations = data;
                this.isLoadingResults = false;
            })
        } else {
            this.locationsService.getAll().subscribe((data) => {
                this.locations = data;
                this.isLoadingResults = false;
            });
        }
    }

}
