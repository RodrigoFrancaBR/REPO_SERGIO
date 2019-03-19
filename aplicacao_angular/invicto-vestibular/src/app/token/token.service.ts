import { Injectable } from '@angular/core';
const KEY = 'authToken';
@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor() { }

  hasToken() {
    // return !!this.getToken();

    const token = this.getToken();
    if (token === null || token === undefined) {
      return false;
    } else {
      return true;
    }

  }

  getToken() {
    return window.localStorage.getItem(KEY);
  }

  setToken(token: any) {
    window.localStorage.setItem(KEY, token);
  }

  removeToken() {
    window.localStorage.removeItem(KEY);
  }
}
