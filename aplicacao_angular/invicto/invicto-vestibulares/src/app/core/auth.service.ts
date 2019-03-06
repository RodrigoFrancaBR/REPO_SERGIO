import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

const API_URL = 'http://localhost:8080/vestibular/servico/usuario';

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  constructor(private httpClient: HttpClient) { }

  authenticate(nome: string, senha: string) {
    console.log('efetuando o login');
    return this.httpClient.post(API_URL, {nome, senha});
  }
}
