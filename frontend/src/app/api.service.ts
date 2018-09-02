import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { environment } from '../environments/environment';
import { AppMarker } from './_models/app-marker';
import {AppMarkerDual} from "./_models/app-marker-dual";


const API_URL = environment.apiUrl;
const MARKERS_URL = API_URL + '/markers';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  markers: AppMarkerDual[] = [
    {
      "oldLocation": {
        "name": "London",
        "lat": 51.4,
        "lng": -0.13
      },
      "newLocation": {
        "name": "Berlin",
        "lat": 52.48,
        "lng": 13.40
      }
    },
    {
      "oldLocation": {
        "name": "Alger",
        "lat": 36.73,
        "lng": 3.07
      },
      "newLocation": {
        "name": "Hamburg",
        "lat": 53.53,
        "lng": 9.98
      }
    },
    {
      "oldLocation": {
        "name": "Denver",
        "lat": 39.74,
        "lng": -104.98
      },
      "newLocation": {
        "name": "Sydney",
        "lat": -33.9,
        "lng": 151.17
      }
    }
  ];
  /** GET all markers from the server */
  getAllMarkers(): Observable<AppMarkerDual[]> {
/*    return this.http.get<AppMarker[]>(MARKERS_URL)
      .pipe(
        catchError(this.handleError<AppMarker[]>('getAllMarkers()', []))
      );*/
    return of(this.markers);
  }

  /*
  return error handler
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }


}
