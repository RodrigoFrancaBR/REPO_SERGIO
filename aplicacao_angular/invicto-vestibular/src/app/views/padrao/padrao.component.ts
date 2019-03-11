import { Usuario } from './../../interfaces/usuario';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { LoginService } from './../login/login.service';

@Component({
  selector: 'app-padrao',
  templateUrl: './padrao.component.html',
  styleUrls: ['./padrao.component.scss']
})
export class PadraoComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private loginService: LoginService) { }
  user: Usuario;

  ngOnInit() {
    console.log('oi');
    const nome = this.activatedRoute.snapshot.params.name;

    this.loginService.efetuarLogin(nome)
      .subscribe((usuario: Usuario) => {

        this.user = usuario,
        (err) => {
          alert ('Invalid username or password!');
        };
      });
}
}
