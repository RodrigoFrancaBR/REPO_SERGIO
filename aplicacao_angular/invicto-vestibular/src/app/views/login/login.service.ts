import { TokenService } from './../../token/token.service';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';

import { UsuarioDTO } from '../../model/dto/usuario.dto';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8080/vestibular/servico/usuario';

@Injectable({
  providedIn: 'root'
})

export class LoginService {

  constructor(private httpClient: HttpClient, private tokenService: TokenService) { }

  // efetuarLogin(nome: string, senha: string) {
  //   return this.httpClient.post(API_URL, {nome, senha});
  // }

  efetuarLogin(usuario: UsuarioDTO): Observable<any> {
    return this.httpClient.post(API_URL, usuario, { observe: 'response' })
      .pipe(tap((res: any) => {
        const authToken = res.headers.get('Authorization');
        console.log(authToken);
        this.tokenService.setToken(authToken);
        // window.localStorage.setItem('authToken', authToken);
      }));
  }
}
