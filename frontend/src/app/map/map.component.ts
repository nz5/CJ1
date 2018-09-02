import { Component, OnInit } from '@angular/core';
import {AppMarker} from '../_models/app-marker';
import * as L from 'leaflet';
import {MapService} from '../map.service';
import {MarkerIcons} from '../marker-icons';
import {environment} from '../../environments/environment';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

  map: L.Map;
  markers: L.Layer[] = [];
  fitBounds: any;
  maxZoom: number = 10;
  minZoom: number = 2;

  markerIcons = new MarkerIcons();
  fromIcon = this.markerIcons.blueIcon;
  toIcon = this.markerIcons.redIcon;

  osmLayer = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    detectRetina: true,
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
  });

  mapBoxLayer = L.tileLayer('https://api.mapbox.com/v4/mapbox.streets/{z}/{x}/{y}.png?access_token=' + environment.mapBoxAccessToken, {
    tileSize: 512,
    zoomOffset: -1,
    detectRetina: true,
    attribution: '© <a href="https://www.mapbox.com/feedback/">Mapbox</a> ' +
      '© <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
  });

  options = {
    layers: [ this.mapBoxLayer ],
    zoom: this.minZoom,
    center: L.latLng([ 43.6, 8.7 ]),
    zoomControl: false // don't add top-left zoom
  };

  constructor(private mapService: MapService) { }

  ngOnInit() {
    this.getAllMarkers();
  }

  getAllMarkers() {
    this.mapService
      .getAllMarkers()
      .subscribe(
        (markers) => {
          this.markers = [];
          // for every AppMarkerDual
          for (let m of markers) {
            // create a leaflet marker (from)
            this.markers.push(L.marker([m.oldLocation.lat, m.oldLocation.lng], {
              icon: this.fromIcon,
              title: m.oldLocation.name
            }));
            this.markers.push(L.marker([m.newLocation.lat, m.newLocation.lng], {
              icon: this.toIcon,
              title: m.newLocation.name
            }));
          }
          // update bounds
          this.boundMap();
        }
      );
  }

  boundMap() {
    let group = L.featureGroup(this.markers);
    console.log(group);
    this.fitBounds = group.getBounds().pad(0.02); // %1
  }

  addLegend(map: L.Map) {
    map.addControl(L.control.zoom({ position: 'bottomright' }));

    // add legend
    let legend = new L.Control({position: 'topleft'});

    let div = L.DomUtil.create('div', 'info legend');
    div.innerHTML += '<h4>Legend</h4>';
    div.innerHTML += '<div class="box"><img src="' + this.fromIcon.options.iconUrl + '"><span>Student Origin</span></div>';
    div.innerHTML += '<div class="box"><img src="' + this.toIcon.options.iconUrl   + '"><span>Student Location</span></div>';

    legend.onAdd = () => {
      return div;
    };

    legend.addTo(map);
  }

  onMapReady(map: L.Map) {
    this.addLegend(map);
  }


}
