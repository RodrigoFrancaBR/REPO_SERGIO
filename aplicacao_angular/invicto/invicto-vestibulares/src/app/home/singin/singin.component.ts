import { Usuario } from './../../interface/usuario';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { AuthService } from './../../core/auth.service';

@Component({
  // selector: 'app-singin',
  templateUrl: './singin.component.html',
  styleUrls: ['./singin.component.css']
})
export class SinginComponent implements OnInit {
  formLogin: FormGroup;
  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    this.iniciarFormulario();
  }

  iniciarFormulario(): void {
    this.formLogin = this.formBuilder.group({
      inputUserName: ['', Validators.required],
      inputPassword: ['', Validators.required]
    });
  }

  login() {
    const userName = this.formLogin.get('inputUserName').value;
    const password = this.formLogin.get('inputPassword').value;

    this.authService
      .authenticate(userName, password)
      .subscribe((resp: Usuario) => {
        this.router.navigate(['user', resp.tipo]),
          console.log('Se autenticou', resp.tipo),
          err => {
            alert('Invalid username or password!');
          };
      });
  }
}
