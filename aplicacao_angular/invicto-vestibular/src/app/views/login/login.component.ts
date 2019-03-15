import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Md5 } from 'ts-md5/dist/md5';

import { Usuario } from './../../model/vo/usuarioVO';
import { LoginService } from './login.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: 'login.component.html'
})
export class LoginComponent implements OnInit {
  usuario: Usuario;
  formLogin: FormGroup;

  constructor(private loginService: LoginService, private formBuilder: FormBuilder, private router: Router) { this.iniciarFormulario(); }

  ngOnInit() {
  }

  iniciarFormulario(): void {
    this.formLogin = this.formBuilder.group({
      inputUserName: ['', Validators.required],
      inputPassword: ['', Validators.required]
    });
  }

  onSubmit(): void {
    const userName = this.formLogin.get('inputUserName').value;
    const password = this.formLogin.get('inputPassword').value;
    
    this.usuario = new Usuario();
    this.usuario.nome = userName;
    this.usuario.senha = password;
    
    // this.usuario.senha = Md5.hashStr(password).toString().split('').reverse().join('');
    this.loginService.efetuarLogin(this.usuario)
      .subscribe((usuario: Usuario) => {
        this.router.navigate(['usuario', usuario.tipo]),
          (err) => {
            alert('Invalid username or password!');
          };
      });

    // // this.loginService.efetuarLogin(userName, password)
    //   .subscribe((usuario: Usuario) => {
    //     this.router.navigate(['usuario', usuario.tipo]),
    //     (err) => {
    //       alert ('Invalid username or password!');
    //     };
    //   });
  }
}
