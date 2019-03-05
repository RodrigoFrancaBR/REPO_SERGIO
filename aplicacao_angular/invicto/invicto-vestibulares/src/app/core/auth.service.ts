import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
const API_URL = 'http://localhost:8080/vestibular/servico/usuario';
export class AuthService {

  constructor(private httpCliente: HttpClient) { }

  authenticate(user: string, password: string) {
    console.log('efetuando o login');
    this.httpCliente.post('');
  }
}
