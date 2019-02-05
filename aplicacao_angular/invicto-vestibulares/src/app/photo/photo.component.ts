import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-photo',
  templateUrl: './photo.component.html',
  styleUrls: ['./photo.component.css']
})
export class PhotoComponent implements OnInit {
  // alguém vai fornecer os dados para o src e alt

  @Input() src = '';
  @Input() alt = '';
  constructor() { }

  ngOnInit() {
  }

}
