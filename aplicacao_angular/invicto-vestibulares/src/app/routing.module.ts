import { NgModule, Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, Router, RouterModule } from '@angular/router';

import { ErrorsModule } from './errors/errors.module';
import { NotFoundComponent } from './errors/not-found/not-found.component';
import { HomeModule } from './home/home.module';
import { SinginComponent } from './home/singin/singin.component';
import { PrincipalComponent } from './views/principal/principal.component';
import { UnidadeModule } from './views/unidade/unidade.module';
import { UnidadeRaizComponent } from './views/unidade/unidade-raiz/unidade-raiz.component';

// import { UnidadeModule } from './unidade/unidade.module';
// import { FormComponent } from './unidade/form/form.component';
// import { TableComponent } from './unidade/table/table.component';
// import { UnidadeComponent } from './unidade/unidade.component';





export const routes: Routes = [
  // { path: 'unidade/form', component: FormComponent },
  // { path: 'unidade/table', component: TableComponent },
  // { path: 'unidade', component: UnidadeComponent },
  { path: 'user/:userName', component: PrincipalComponent },
  { path: 'views/unidade/unidade-raiz', component: UnidadeRaizComponent },
  { path: '', component: SinginComponent },
  { path: '**', component: NotFoundComponent }

  //
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    ErrorsModule,
    HomeModule,
    UnidadeModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class RoutingModule {}
