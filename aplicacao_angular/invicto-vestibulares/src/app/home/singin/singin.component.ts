import { UnidadeGridComponent } from './../../views/unidade/unidade-raiz/unidade-grid/unidade-grid.component';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

import { Usuario } from './../../model/usuario';
@Component({
  // selector: 'app-singin',
  templateUrl: './singin.component.html',
  styleUrls: ['./singin.component.css']
})
export class SinginComponent implements OnInit {
  formLogin: FormGroup;
  usuario: Usuario;
  constructor(private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.iniciarFormulario();
  }

  iniciarFormulario(): void {
    this.formLogin = this.formBuilder.group({
      inputUsuario: ['', Validators.required],
      inputSenha: ['', Validators.required]
    });
  }

}
