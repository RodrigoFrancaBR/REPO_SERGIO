import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';

import { AppComponent } from './app.component';
import { RoutingModule } from './routing.module';
import { AdminComponent } from './views/admin/admin.component';
import { OperadorComponent } from './views/operador/operador.component';

@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    OperadorComponent

  ],
  imports: [
    BrowserModule, RoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
