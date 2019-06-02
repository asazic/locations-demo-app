import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import Filter from '../models/filter.model';
import LocationModel from '../models/location.model';

@Injectable()
export default class LocationsService {

    private apiUrl = 'http://localhost:8080/api';

    constructor(private _http: HttpClient) { }

    public getAll(): Observable<LocationModel[]> {
        return this._http.get<LocationModel[]>(`${this.apiUrl}/locations`);
    }

    public getFiltered(filter: Filter): Observable<LocationModel[]> {
        return this._http.get<LocationModel[]>(`${this.apiUrl}/locations/filtered?addressId=${filter.addressId}&radius=${filter.radius}`);
    }

    public getSpecific(id: string): Observable<LocationModel> {
        return this._http.get<LocationModel>(`${this.apiUrl}/locations/${id}`);
    }

    public update(id: string, location: LocationModel): void {
        this._http.put<LocationModel>(`${this.apiUrl}/locations/${id}`, location);
    }

    public create(location: LocationModel): Observable<LocationModel> {
        return this._http.post<LocationModel>(`${this.apiUrl}/locations`, location);
    }
}
