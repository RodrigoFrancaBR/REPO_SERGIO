import { AuthService } from './../../core/auth.service';
import { Md5 } from 'ts-md5/dist/md5';
import { Component, OnInit } from '@angular/core';
import { UsuarioDTO } from 'app/model/dto/usuario.dto';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit {
  usuario: UsuarioDTO = new UsuarioDTO();

  formLogin: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router, private authService: AuthService) { }

  ngOnInit() {
    this.iniciarFormulario();
  }

  iniciarFormulario(): void {
    this.formLogin = this.formBuilder.group({
      inputUserName: ['', Validators.required],
      inputPassword: ['', Validators.required],
      inputMatricula: ['', Validators.required]
    });
  }

  onSubmit(): void {
    const userName = this.formLogin.get('inputUserName').value;
    const password = this.formLogin.get('inputPassword').value;
    const matricula = this.formLogin.get('inputMatricula').value;

    this.usuario.nome = userName;
    this.usuario.matricula = matricula;
    // this.usuario.senha = password;
    this.usuario.senha = Md5.hashStr(password).toString().split('').reverse().join('');
    console.log(this.usuario);

    this.authService.efetuarLogin(this.usuario)
      .subscribe((resp) => {
        this.usuario = resp.body;
        // console.log(JSON.stringify(resp.body.nome));
        this.router.navigate(['default']),
        // this.router.navigate(['dashboard']),
        // this.router.navigate(['usuario', this.usuario.tipo]),
          (error: any) => {
            console.log(error);
          };
      });

    // this.loginService.efetuarLogin(this.usuario)
    //   .subscribe((usuario: Usuario) => {        
    //     this.router.navigate(['usuario', usuario.tipo]),
    //       (err: any) => {
    //         this.inputUserName.nativeElement.focus();
    //         console.log(err);
    //       };
    //   });

    // // this.loginService.efetuarLogin(userName, password)
    //   .subscribe((usuario: Usuario) => {
    //     this.router.navigate(['usuario', usuario.tipo]),
    //     (err) => {
    //       alert ('Invalid username or password!');
    //     };
    //   });
  }

  

}
