import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

const API_URL = 'http://localhost:8080/vestibular/servico';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private httpCliente: HttpClient) { }

  efetuarLogin(usuario: string, senha: string) {
    console.log('efetuando o login');
    this.httpCliente.post(API_URL + '/usuario');
  }
}
