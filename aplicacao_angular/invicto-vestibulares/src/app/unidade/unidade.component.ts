import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Unidade } from './unidade';
import { UnidadeDTO } from './unidade.dto';
import { UnidadeService } from './unidade.service';

@Component({
  selector: 'app-unidade',
  templateUrl: './unidade.component.html',
  styleUrls: ['./unidade.component.css']
})
export class UnidadeComponent implements OnInit {
  titulo = 'Unidades';
  unidades: Unidade[] = [];
  constructor(private unidadeService: UnidadeService) { }

  ngOnInit() {
    this.unidadeService.getUnidades().subscribe((unidades) => {
      this.unidades = unidades;
      console.log(unidades[0].nome);
    });
  }

  // executarSalvarUnidade(dto: UnidadeDTO) {
  //   this.unidadeService.salvarUnidade(dto).subscribe(
  //     () => console.log('salvo'),
  //     err => {
  //       console.log(err);
  //     }

  //   });

}
