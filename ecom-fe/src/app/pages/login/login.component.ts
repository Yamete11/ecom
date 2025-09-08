import {ChangeDetectionStrategy, Component, signal} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  template: `
    <div class="login-container">
      <h1>Login</h1>
      <form [formGroup]="loginForm" (ngSubmit)="submit()">
        <label>
          Login:
          <input type="text" formControlName="login"/>
        </label>
        <label>
          Password:
          <input type="password" formControlName="password"/>
        </label>
        <button type="submit" [disabled]="loginForm.invalid">Login</button>
      </form>
      @if (errorMessage()){
        <p>{{ errorMessage() }}</p>
      }
    </div>
  `,
  styles: [`
    .login-container {
      max-width: 400px;
      margin: auto;
      padding: 2rem;
      border: 1px solid #ccc;
      border-radius: 8px;
    }
    label {
      display: block;
      margin-bottom: 1rem;
    }
    input {
      width: 100%;
      padding: 0.5rem;
      margin-top: 0.2rem;
    }
    button {
      padding: 0.5rem 1rem;
      margin-top: 1rem;
    }
    p {
      color: red;
      margin-top: 1rem;
    }
  `],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class LoginComponent {
  loginForm = new FormGroup({
    login: new FormControl('', [Validators.required]),
    password: new FormControl('', Validators.required)
  });

  errorMessage = signal<string | null>(null);

  constructor(private router: Router, private authService: AuthService) {}

  submit() {
    const { login, password } = this.loginForm.value;

    this.authService.login(login!, password!).subscribe({
      next: () => this.router.navigate(['/products']),
      error: () => this.errorMessage.set('Invalid email or password')
    });
  }
}
