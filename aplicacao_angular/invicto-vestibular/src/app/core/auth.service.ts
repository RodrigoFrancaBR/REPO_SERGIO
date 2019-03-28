import { UsuarioService } from './usuario/usuario.service';
import { TokenService } from './../token/token.service';
import { tap } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UsuarioDTO } from 'app/model/dto/usuario.dto';

const API_URL = 'http://localhost:8080/vestibular/servico/usuario';

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  constructor(private httpClient: HttpClient, private tokenService: TokenService, private usuarioService: UsuarioService) { }

  efetuarLogin(usuario: UsuarioDTO) {
    return this.httpClient.post(API_URL, usuario, { observe: 'response' })
      .pipe(tap((res: any) => {
        const authToken = res.headers.get('Authorization');
        console.log(authToken);
        // this.tokenService.setToken(authToken);
        this.usuarioService.setToken(authToken);
        // window.localStorage.setItem('authToken', authToken);
      }));
  }
}
