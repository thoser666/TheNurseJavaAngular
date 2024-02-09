import { ApplicationConfig } from '@angular/core';
import { provideRouter, withEnabledBlockingInitialNavigation, } from '@angular/router';
// import { routes } from './app.routes';
import { appRoutes } from './app.routes';

export const appConfig: ApplicationConfig = {
 // providers: [provideRouter(routes)]
  providers: [provideRouter(appRoutes, withEnabledBlockingInitialNavigation())],
};
