import { Usuario } from './../../model/vo/usuarioVO';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

const API_URL = 'http://localhost:8080/vestibular/servico/usuario';

@Injectable({
  providedIn: 'root'
})

export class LoginService {

  constructor(private httpClient: HttpClient) { }

  // efetuarLogin(nome: string, senha: string) {
  //   return this.httpClient.post(API_URL, {nome, senha});
  // }

  efetuarLogin(usuario: Usuario) {
    return this.httpClient.post(API_URL, usuario);
  }
}
