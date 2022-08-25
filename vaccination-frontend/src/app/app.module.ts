import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { authInterceptorProviders } from 'src/assets/interceptor/auth.interceptor';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { TemplateModule } from './template/template.module';

@NgModule({
  bootstrap: [AppComponent],
  declarations: [
    AppComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserAnimationsModule,
    BrowserModule,
    HttpClientModule,
    TemplateModule
  ],
  providers: [authInterceptorProviders]
})
export class AppModule { }