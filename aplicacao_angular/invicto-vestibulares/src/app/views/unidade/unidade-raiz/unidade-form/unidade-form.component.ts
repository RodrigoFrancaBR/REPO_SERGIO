import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UnidadeInterface } from 'src/app/interface/unidade.interface';

@Component({
  selector: 'app-unidade-form',
  templateUrl: './unidade-form.component.html',
  styleUrls: ['./unidade-form.component.css']
})
export class UnidadeFormComponent implements OnInit {
  formularioUnidade: FormGroup;
  constructor(private formBuilder: FormBuilder, ) { }

  ngOnInit() {
    this.iniciarFormulario();
  }
  iniciarFormulario(): void {
    this.formularioUnidade = this.formBuilder.group({
      inputNome: ['', Validators.required],
      inputEndereco: ['', Validators.required],
    });
  }

}
