//import { Routes } from '@angular/router';
import { Route } from '@angular/router';
import {UserFormComponent} from "./user-form/user-form.component";

export const appRoutes: Route = [
  {
    path: '',
    component: UserFormComponent,
    pathMatch: 'full',
  },
];
