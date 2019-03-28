import { TokenService } from './../token/token.service';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { UsuarioInterface } from 'app/interface/usuario.interface';
import * as jtw_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private tokenService: TokenService) {
    this.tokenService.hasToken() && this.decodeAndNotify();
  }

  private usuarioSubject = new Subject<UsuarioInterface>();

  setToken(token: string) {
    this.tokenService.setToken(token);
    this.decodeAndNotify();
  }

  getUsuario() {
    return this.usuarioSubject.asObservable();
  }

  private decodeAndNotify(): void {
    const token = this.tokenService.getToken();
    const usuario = jtw_decode(token) as UsuarioInterface;
    this.usuarioSubject.next(usuario);
  }
}
