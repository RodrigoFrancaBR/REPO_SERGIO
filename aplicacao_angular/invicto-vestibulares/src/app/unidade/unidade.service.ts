import { Unidade } from './unidade';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

const API = 'http://localhost:8080/invictoVestibulares/invicto';
@Injectable({
  providedIn: 'root'
})
export class UnidadeService {

  constructor(private http: HttpClient) { }

  getUnidades() {
    return this.http
    .get<Unidade[]>(API + '/unidade/unidades');
  }
}
