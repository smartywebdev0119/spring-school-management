import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {User} from "../model/user";
import {AuthService} from "./auth.service";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  public user: User | undefined

  constructor(private http: HttpClient,
              private authService: AuthService,
              private router: Router) {
    console.log("User constructor")
    if (this.authService.authenticated.value) {
      this.getLoggedInUser()
    } else {
      this.authService.authenticated.subscribe((authenticated) => {
        if (authenticated) {
          this.getLoggedInUser()
        } else {
          this.user = undefined
        }
      })
    }
  }

  getLoggedInUserObservable() {
    return this.http.get<User>(`${environment.apiUrl}/users/me`)
  }

  getLoggedInUser() {
    this.http.get<User>(`${environment.apiUrl}/users/me`).subscribe((result) => {
      this.user = result;
    })
  }

  updateAbout(newAbout: string) {
    return this.http.put(`${environment.apiUrl}/users/me/description`, {description: newAbout}, {observe: "response"})
  }

  changePassword(oldPassword: string, newPassword: string) {
    return this.http.put(`${environment.apiUrl}/users/me/password`, {
      oldPassword: oldPassword,
      newPassword: newPassword
    }, {observe: "response"})
  }
}
