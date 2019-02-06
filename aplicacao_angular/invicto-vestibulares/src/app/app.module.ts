import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';

// import { PhotoComponent } from './photo/photo.component';
import { UnidadeModule } from './unidade/unidade.module';

@NgModule({
  declarations: [
    AppComponent,
    // PhotoComponent
  ],
  imports: [
    BrowserModule,
    UnidadeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
