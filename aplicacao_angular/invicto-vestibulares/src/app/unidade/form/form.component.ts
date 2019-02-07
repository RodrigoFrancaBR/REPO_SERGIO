import { UnidadeService } from './../unidade.service';
import { UnidadeDTO } from './../unidade.dto';
import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {
  formUnidade: FormGroup;
  // suporte para os dados do formulário
  paramUnidade: UnidadeDTO = new UnidadeDTO();

  @Output()
  executarSalvar = new EventEmitter();

  constructor(private fb: FormBuilder, private unidadeService: UnidadeService) { }

  ngOnInit() {
    this.iniciarFormulario();
  }

  iniciarFormulario() {
    this.formUnidade = this.fb.group({
      unidadeNome: ['', Validators.required],
      unidadeEndereco: ['', Validators.required],
    });
  }

  salvarUnidade() {
    console.log('salvarUnidade()');
    // verificar se o form está valido
    // conversão de datas
    // tratamento de null
    if (this.formUnidade.valid) {
      console.log('Formulário Válido');
      console.log(this.paramUnidade)
      this.paramUnidade.nome = this.formUnidade.get('unidadeNome').value;
      this.paramUnidade.endereco = this.formUnidade.get('unidadeEndereco').value;
      this.unidadeService.salvarUnidade(this.paramUnidade).subscribe(
        (res) => {
          console.log(res),
            // tslint:disable-next-line:no-unused-expression
            (err) => {
              console.log(err);
            }
          this.formUnidade.reset();
        });
      //  this.unidadeService.paramUnidade = this.paramUnidade;
      // this.executarSalvar.emit(this.paramUnidade);
    } else {
      console.log('Formulário Inválido');
    }
  }
}
