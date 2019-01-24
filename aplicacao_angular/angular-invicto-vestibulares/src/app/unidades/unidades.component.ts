import { Component, OnInit } from '@angular/core';
import { Unidade } from '../unidade';

@Component({
  selector: 'app-unidades',
  templateUrl: './unidades.component.html',
  styleUrls: ['./unidades.component.css']
})
export class UnidadesComponent implements OnInit {
  unidade: Unidade = {
    id: 1,
    nome: 'Cascadura',
    endereco: 'Quintão 153',
    ativo: true,
  };
  constructor() { }

  ngOnInit() {
  }

}
