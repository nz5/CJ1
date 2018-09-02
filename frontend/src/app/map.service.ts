import { Injectable } from '@angular/core';
import { ApiService } from './api.service';

import { AppMarker } from './_models/app-marker';
import { Observable } from 'rxjs';
import { AppMarkerDual } from "./_models/app-marker-dual";

@Injectable({
  providedIn: 'root'
})
export class MapService {

  constructor(private api: ApiService) { }

  getAllMarkers(): Observable<AppMarkerDual[]> {
    return this.api.getAllMarkers();
  }
}
