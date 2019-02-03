import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  photos = [
    {
      // tslint:disable-next-line:max-line-length
      src : 'https://scontent.fgig4-1.fna.fbcdn.net/v/t1.0-9/32805056_1045883828901028_1834317013850259456_n.jpg?_nc_cat=102&_nc_ht=scontent.fgig4-1.fna&oh=15c53739e77ae3a8939ab4611ef049e3&oe=5CFF9ECA',
      alt : 'Invicto1'
    },
    {
      // tslint:disable-next-line:max-line-length
      src : 'https://scontent.fgig4-1.fna.fbcdn.net/v/t1.0-9/32207890_1041794692643275_1138426376529903616_o.jpg?_nc_cat=109&_nc_ht=scontent.fgig4-1.fna&oh=5d9b36979e165fd72c55f50a40fcd7ea&oe=5CF983C8',
      alt : 'Invicto2'
    }
  ];
// tslint:disable-next-line:max-line-length
// url = 'https://scontent.fgig4-1.fna.fbcdn.net/v/t1.0-9/32207890_1041794692643275_1138426376529903616_o.jpg?_nc_cat=109&_nc_ht=scontent.fgig4-1.fna&oh=5d9b36979e165fd72c55f50a40fcd7ea&oe=5CF983C8';
// alt = 'Invicto';
}
