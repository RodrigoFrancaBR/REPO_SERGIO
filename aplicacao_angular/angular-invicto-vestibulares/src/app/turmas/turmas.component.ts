import { Component, OnInit } from '@angular/core';
import { Unidade } from '../unidade';
import { Turma } from '../turna';

@Component({
  selector: 'app-turmas',
  templateUrl: './turmas.component.html',
  styleUrls: ['./turmas.component.css']
})
export class TurmasComponent implements OnInit {
  unidade: Unidade = {
    id: 1,
    nome: 'Madureira',
    endereco: 'Carolina Machado 666',
    ativo: true,
  };
  turma: Turma = {
  unidade: this.unidade,
  id: 1,
  nome: '1001',
  turno: 'Manhã',
  ativo: true,
  };
  constructor() { }

  ngOnInit() {
  }

}
