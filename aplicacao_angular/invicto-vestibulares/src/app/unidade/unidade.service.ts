import { UnidadeDTO } from './unidade.dto';
import { Unidade } from './unidade';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

const API = 'http://localhost:8080/invictoVestibulares/invicto';
// const API = 'https://invictovestibulares.azurewebsites.net/invicto';

@Injectable({
  providedIn: 'root'
})
export class UnidadeService {
  paramUnidade: UnidadeDTO = new UnidadeDTO();

  constructor(private http: HttpClient) { }

  getUnidades() {
    return this.http
      .get<Unidade[]>(API + '/unidade/unidades');
  }
  getUnidade(unidade: UnidadeDTO): Observable<any> {
    return this.http.get(API + '/unidade/unidade?nome=' + unidade.nome);
  }

  salvarUnidade(unidade: UnidadeDTO) {
    console.log('salvarUnidade(unidade: UnidadeDTO);');
    console.log('Unidade Salva');
    console.log(unidade.nome);
    console.log(unidade.endereco);
    return this.http.post(API + '/unidade', unidade);
  }
}
