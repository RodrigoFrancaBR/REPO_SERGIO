import { Unidade } from './../../model/vo/unidade';
import { Component, OnInit, Output } from '@angular/core';
import { EventEmitter } from '@angular/core';

@Component({
  templateUrl: 'my-tables.component.html'
})
export class MyTablesComponent implements OnInit {

  constructor() { }

  @Output() unidadeEmitter = new EventEmitter();

  unidades: Unidade[] = [];

  unidadeSelecionada: Unidade = new Unidade();

  pageUnidade: number;

  ngOnInit(): void {
    console.log('aqui');
    const unidade = new Unidade();
    unidade.id = 1;
    unidade.nome = 'Cascadura';
    unidade.endereco = 'Quintão 190';
    unidade.status = 'Ativo';

    const unidade2 = new Unidade();
    unidade2.id = 2;
    unidade2.nome = 'Madureira';
    unidade2.endereco = 'Carolina Machado';
    unidade2.status = 'Inativo';

    this.unidades.push(unidade);
    this.unidades.push(unidade2);
    console.log(this.unidades);
  }

  isSelecionado(item: Unidade) {
    if (this.unidadeSelecionada.id === item.id) {
      return true;
    } else {
      return false;
    }
  }
  onSelecionarItem(item: Unidade): void {
    this.unidadeSelecionada = item;
    this.unidadeEmitter.emit(this.unidadeSelecionada);
  }
}
