import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { appRoutes } from './app.routes';
import { OktaCallbackComponent, OktaAuthGuard } from '@okta/okta-angular';


export const appConfig: ApplicationConfig = {
  providers: [provideRouter(appRoutes)],
};
