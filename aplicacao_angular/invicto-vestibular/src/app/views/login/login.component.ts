import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Md5 } from 'ts-md5/dist/md5';

// import { Usuario } from './../../interface/usuario';
import { LoginService } from './login.service';
import { Usuario } from '../../interfaces/usuario';

@Component({
  selector: 'app-dashboard',
  templateUrl: 'login.component.html'
})
export class LoginComponent implements OnInit {

  formLogin: FormGroup;

  constructor(private loginService: LoginService,
    private formBuilder: FormBuilder,
    // private authService: AuthService,
    private router: Router, private usuario: Usuario
  ) { this.iniciarFormulario(); }

  ngOnInit() {
    this.iniciarFormulario();
  }

  iniciarFormulario(): void {
    this.formLogin = this.formBuilder.group({
      inputUserName: ['', Validators.required],
      inputPassword: ['', Validators.required]
    });
  }

  onSubmit(): void {
    // this.usuario.senha = Md5.hashStr(this.usuario.senha).toString().split('').reverse().join('');
    // this.loginService.efetuarLogin(this.usuario);

    const userName = this.formLogin.get('inputUserName').value;
    const password = this.formLogin.get('inputPassword').value;

    // this.loginService.efetuarLogin(userName, password)
    //   .subscribe((usuario: Usuario) => {
    //     this.router.navigate(['usuario', usuario.tipo]),
    //     (err) => {
    //       alert ('Invalid username or password!');
    //     };
    //   });
  }
}
