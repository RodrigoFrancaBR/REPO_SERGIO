import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  // selector: 'app-singin',
  templateUrl: './singin.component.html',
  styleUrls: ['./singin.component.css']
})
export class SinginComponent implements OnInit {
  formLogin: FormGroup;
  constructor(private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.iniciarFormulario();
  }

  iniciarFormulario(): void {
    this.formLogin = this.formBuilder.group({
      inputUser: ['', Validators.required],
      inputPassword: ['', Validators.required]
    });
  }
  login() {
    console.log(this.formLogin.get('inputUser').value);
    console.log(this.formLogin.get('inputPassword').value);
  }

}
