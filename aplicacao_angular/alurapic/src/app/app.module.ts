import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';

import { PhotosModule } from './photos/photos.module';
import { AppComponent } from './app.component';

// import { PhotoComponent } from './photo/photo.component';

@NgModule({
  declarations: [
    AppComponent,
    // PhotoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    PhotosModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
