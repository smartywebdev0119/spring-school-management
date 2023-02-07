import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {ThemeService} from "../../services/theme.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
})
export class LoginComponent implements OnInit {

  username = "";
  password = "";

  constructor(private authService: AuthService,
              private router: Router,
              public themeService: ThemeService) {
  }

  ngOnInit(): void {
  }

  login() {
    this.authService.login(this.username, this.password).subscribe((result) => {
      this.authService.setLoggedInUser(result)
      this.router.navigate(["/"])
      console.log(this.authService.getRole())
    }, error => {
      alert("Wrong credentials")
    })
  }
}