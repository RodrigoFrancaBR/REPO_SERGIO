import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UnidadeService {
  const API = 'localhost:8080';
  constructor(private http: HttpClient) { }
  getUnidades(userName: string){
    return this.http.get<Object[]>
    (API + '/invictoVestibulares/invicto/unidade/unidades');
  }
}
