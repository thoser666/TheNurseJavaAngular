import { Route } from '@angular/router';
import { LoginComponent } from '../../auth/login/login.component';
import { SignupComponent } from '../../auth/signup/signup.component';

export const appRoutes: Route[] = [
  {path: 'login', component: LoginComponent},
  {path: 'signup', component: SignupComponent},
];
