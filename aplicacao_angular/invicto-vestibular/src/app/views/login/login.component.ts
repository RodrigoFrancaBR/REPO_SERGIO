import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Md5 } from 'ts-md5/dist/md5';

import { Usuario } from './../../model/vo/usuarioVO';
import { LoginService } from './login.service';
import { STRING_TYPE } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-dashboard',
  templateUrl: 'login.component.html'
})
export class LoginComponent implements OnInit {
  usuario: Usuario;
  formLogin: FormGroup;
  // tslint:disable-next-line:semicolon
  // vai no template e injeta aqui, pega uma referencia do template
  @ViewChild('inputUserName') inputUserName: ElementRef<HTMLInputElement>;

  constructor(private loginService: LoginService, private formBuilder: FormBuilder, private router: Router) { this.iniciarFormulario(); }

  ngOnInit() {
    this.usuario = new Usuario();
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

    this.usuario.nome = userName;
    // this.usuario.senha = password;
    this.usuario.senha = Md5.hashStr(password).toString().split('').reverse().join('');

    // this.loginService.efetuarLogin(this.usuario)
    // .subscribe((resp) => {
    //   console.log(JSON.stringify(resp)),         
    //        (error: any) => {
    //          console.log(error);
    //        };       
    // });

    this.loginService.efetuarLogin(this.usuario)
      .subscribe((usuario: Usuario) => {
        console.log(JSON.stringify(usuario));
        this.router.navigate(['usuario', usuario.tipo]),
          (err: any) => {
            this.inputUserName.nativeElement.focus();
            console.log(err);
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
