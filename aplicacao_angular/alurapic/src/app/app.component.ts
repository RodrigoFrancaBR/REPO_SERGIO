import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  photos = [
     {
      // tslint:disable-next-line:max-line-length
      src : 'https://scontent.fgig4-1.fna.fbcdn.net/v/t1.0-9/32207890_1041794692643275_1138426376529903616_o.jpg?_nc_cat=109&_nc_ht=scontent.fgig4-1.fna&oh=5d9b36979e165fd72c55f50a40fcd7ea&oe=5CF983C8',
      alt : 'Invicto2'
    }
  ];
  unidades: Object[] = [];
  constructor(http: HttpClient) {
    console.log(http);
    // tslint:disable-next-line:no-unused-expression
    http.get<Object[]>('http://localhost:8080/invictoVestibulares/invicto/unidade/unidades')
    .subscribe(
      unidades => this.unidades = unidades,
      err => console.log(err)
      );
  }
}
