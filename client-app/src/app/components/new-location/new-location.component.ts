import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import LocationModel from 'src/app/models/location.model';

@Component({
    selector: 'app-new-location',
    templateUrl: './new-location.component.html',
    styleUrls: ['./new-location.component.css']
})
export class NewLocationComponent {

    constructor(public dialogRef: MatDialogRef<NewLocationComponent>,
        @Inject(MAT_DIALOG_DATA) public location: LocationModel) {
        this.location = new LocationModel();
    }

    onNoClick(): void {
        this.dialogRef.close();
    }

}
