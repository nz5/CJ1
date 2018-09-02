// This file can be replaced during build by using the `fileReplacements` array.
// `ng build ---prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,

  // URL of development API
  apiUrl: 'http://localhost:3000',
  mapBoxAccessToken: 'pk.eyJ1IjoibG9yYW50LXZyaW5jZWFudSIsImEiOiJjamxiOGp1dTAxNjEyM2ttdGM1MGM4M2NjIn0.3d-K_t4XpZ7RhojAtPPvvQ'
};

/*
 * In development mode, for easier debugging, you can ignore zone related error
 * stack frames such as `zone.run`/`zoneDelegate.invokeTask` by importing the
 * below file. Don't forget to comment it out in production mode
 * because it will have a performance impact when errors are thrown
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
