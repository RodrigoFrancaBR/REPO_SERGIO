import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {
  formUnidade: FormGroup;

  constructor(private fb: FormBuilder) { }

  ngOnInit() {
    this.iniciarFormulario();
  }

  iniciarFormulario() {
    this.formUnidade = this.fb.group({
      nomeUnidade: ['', Validators.required],
      enderecoUnidade: ['', Validators.required],
    });
  }

  salvarUnidade() {
    console.log('ok');
  }

}
