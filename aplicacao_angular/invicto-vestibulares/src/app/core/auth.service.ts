import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpCliente: HttpClient) { }

  efetuarLogin(usuario: string, senha: string) {
    this.httpCliente.post('')
  }
}
