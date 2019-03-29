import { Component } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './app-header.component.html'
})
export class AppHeaderComponent {

  usuarioLogado: string;

  constructor() {
    this.usuarioLogado = localStorage.getItem('chave');
    console.log(this.usuarioLogado);
  }

}
