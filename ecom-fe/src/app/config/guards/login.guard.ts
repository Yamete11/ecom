import { Injectable, inject } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService} from '../../services/auth.service';

@Injectable({ providedIn: 'root' })
export class LoginGuard implements CanActivate {
  private auth = inject(AuthService);
  private router = inject(Router);

  canActivate(): boolean {
    if (this.auth.isLoggedIn()) {
      this.router.navigate(['/products']);
      return false;
    }
    return true;
  }
}
