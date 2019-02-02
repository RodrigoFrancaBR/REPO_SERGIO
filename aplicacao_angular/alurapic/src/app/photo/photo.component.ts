import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-photo',
  templateUrl: './photo.component.html',
  styleUrls: ['./photo.component.css']
})
export class PhotoComponent implements OnInit {
// tslint:disable-next-line:max-line-length
src = 'https://scontent.fgig4-1.fna.fbcdn.net/v/t1.0-9/32207890_1041794692643275_1138426376529903616_o.jpg?_nc_cat=109&_nc_ht=scontent.fgig4-1.fna&oh=5d9b36979e165fd72c55f50a40fcd7ea&oe=5CF983C8';
alt = 'Invicto';

  constructor() { }

  ngOnInit() {
  }

}
