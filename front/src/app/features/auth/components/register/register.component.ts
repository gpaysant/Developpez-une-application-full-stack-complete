import { Component, HostListener } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { RegisterRequest } from '../../interfaces/registerRequest.interface';
import { AuthSuccess } from '../../interfaces/authSuccess.interface';
import { User } from 'src/app/features/user/interfaces/user.interface';
import { UserService } from 'src/app/features/user/services/user.service';
import { SessionService } from 'src/app/services/session.service';
import { HomeService } from 'src/app/services/home.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  public hide = true;
  public onError = false;
  isSmallScreen: boolean = false;
  StrongPasswordRegx: RegExp =/^(?=[^A-Z]*[A-Z])(?=[^a-z]*[a-z])(?=\D*\d).{8,}$/;

  public form = this.fb.group({
    email: [
      '',
      [
        Validators.required,
        Validators.email
      ]
    ],
    username: [
      '',
      [
        Validators.required,
        Validators.min(3),
        Validators.max(20)
      ]
    ],
    password: [
      '',
      [
        Validators.required,
        Validators.pattern(this.StrongPasswordRegx)
      ]
    ]
  });
  

  constructor(private authService: AuthService,
              private userService: UserService,
              private sessionService: SessionService,
              private fb: FormBuilder,
              private router: Router,
              private homeService: HomeService) {
  }


  public submit(): void {
    const registerRequest = this.form.value as RegisterRequest;
    this.authService.register(registerRequest).subscribe(
      (response: AuthSuccess) => {
        localStorage.setItem('token', response.token);
        this.userService.me().subscribe((user: User) => {
          this.sessionService.logIn(user);
          this.router.navigate(['/post'])
        });
      },
      error => {
        this.onError = true;
      }
    );
  }

  back() : void {
    this.homeService.goHome();
  }

  @HostListener('window:resize', ['$event'])
  onResize(event : Event) : void {
      this.checkScreenSize();
  }

  checkScreenSize() {
      this.isSmallScreen = window.innerWidth < 768;
  }

  get passwordFormField() {
    return this.form.get('password');
  }

}