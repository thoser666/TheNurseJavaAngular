import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
//import { HeaderComponent } from 'src/app/header/header.component';
import { Modals } from '../../src/app/modals';
import { AuthService } from '../service/auth.service';
import { LoginRequestPayload } from './login-request.payload';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  standalone: true,
  imports: [
    ReactiveFormsModule
  ]
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loginRequestPayload: LoginRequestPayload;
  registerSuccessMessage: string;
  isError: boolean;
  showMessage: boolean = false;

  constructor(
    private authService: AuthService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private modals: Modals
  ) {
    this.isError = false;
    this.registerSuccessMessage = '';
    this.loginForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
    });
    this.loginRequestPayload = {
      username: '',
      password: '',
    };
    this.activatedRoute.queryParams.subscribe((params: any) => {
      if (
        params['registered'] !== undefined &&
        params['registered'] === 'true'
      ) {
        modals.customSuccessNotification(
          'Succesfull registration, please check your email for verification'
        );
      }
    });
  }

  ngOnInit(): void {}

  login() {
    this.loginRequestPayload.username = this.loginForm.get('username')!.value;
    this.loginRequestPayload.password = this.loginForm.get('password')!.value;
    this.showMessage = true;

    // this.authService.login(this.loginRequestPayload).subscribe({
    //   next: (data) => this.router.navigateByUrl('/home'),
    //   error: (error) => this.modals.errorNotification(error.error),
    // });
  }
}
