import { Usuario } from './../../model/vo/usuarioVO';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { LoginService } from './../login/login.service';

@Component({
  selector: 'app-padrao',
  templateUrl: './padrao.component.html',
  styleUrls: ['./padrao.component.scss']
})
export class PadraoComponent implements OnInit {
  usuario: Usuario;
  constructor(private activatedRoute: ActivatedRoute, private loginService: LoginService, private router: Router) { }


  ngOnInit() {
    console.log('oi');
    this.usuario = new Usuario();
    const nome = this.activatedRoute.snapshot.params.name;
    this.usuario.nome = nome;
    this.loginService.efetuarLogin(this.usuario)
      .subscribe((usuario: Usuario) => {
        this.router.navigate(['usuario', usuario.tipo]),
          (err) => {
            alert('Invalid username or password!');
          };
      });
  }
}
