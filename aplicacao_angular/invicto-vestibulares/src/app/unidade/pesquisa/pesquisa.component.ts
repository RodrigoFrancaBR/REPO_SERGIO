import { UnidadeDTO } from './../unidade.dto';
import { UnidadeService } from './../unidade.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-pesquisa',
  templateUrl: './pesquisa.component.html',
  styleUrls: ['./pesquisa.component.css']
})
export class PesquisaComponent implements OnInit {
  formPesquisaUnidade: FormGroup;
  paramUnidade: UnidadeDTO = new UnidadeDTO();

  @Output()
  execucaoPesquisa = new EventEmitter();

  constructor(private fb: FormBuilder, private unidadeService: UnidadeService) { }

  ngOnInit() {
    this.iniciarFormulario();
  }

  iniciarFormulario() {
    this.formPesquisaUnidade = this.fb.group({
      imputNome: ['', Validators.required],
      // imputEndereco: ['', Validators.required]
    });
  }

  pesquisarUnidade() {
    console.log('pesquisarUnidade()');
    if (this.formPesquisaUnidade.valid) {
      this.paramUnidade.nome = this.formPesquisaUnidade.get('imputNome').value;
      // this.paramUnidade.endereco = this.formPesquisaUnidade.get('imputEndereco').value;
      console.log(this.paramUnidade.nome);
      // console.log(this.paramUnidade.endereco);
      this.execucaoPesquisa.emit(this.paramUnidade);
    }
  }

}
