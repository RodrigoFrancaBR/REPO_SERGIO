import { FormGroup } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

import { Usuario } from './../../model/usuario';
@Component({
  // selector: 'app-singin',
  templateUrl: './singin.component.html',
  styleUrls: ['./singin.component.css']
})
export class SinginComponent implements OnInit {
  formLogin: FormGroup;
  usuario: Usuario;
  constructor() { }

  ngOnInit() {
  }

}
