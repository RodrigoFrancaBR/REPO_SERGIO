import { Component, OnInit, Input } from '@angular/core';

import { Unidade } from '../unidade';
import { UnidadeService } from '../unidade.service';
import { UnidadeDTO } from '../unidade.dto';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {
  // @Input() unidades = '';
  cabecalhoPagina = 'Invicto Vestibulares!';
  cabecalhoTabela = 'Unidades';
  // unidades: Unidade[] = [];
  @Input()
  unidades: UnidadeDTO[] = [];

  constructor(private unidadeService: UnidadeService) { }

  ngOnInit() {
    this.unidadeService.getUnidades().subscribe((unidades) => {
      this.unidades = unidades;
      console.log(unidades[0].nome);
    });
  }

}
