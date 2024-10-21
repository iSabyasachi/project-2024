import { NgModule, provideExperimentalZonelessChangeDetection } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ZoneChangeDetectionComponent } from './zone-change-detection/zone-change-detection.component';
import { SignalChangeDetectionComponent } from './signal-change-detection/signal-change-detection.component';
import { ParentComponent } from './signal/temperature/parent/parent.component';
import { ChildComponent } from './signal/temperature/child/child.component';
import { FormsModule } from '@angular/forms';
import { NonSignalComponent } from './non-signal/non-signal.component';
import { SignalComponent } from './signal/signal.component';
import { RxjsComponent } from './rxjs/rxjs.component';
import { CurrencyComponent } from './signal/currency/currency.component';
import { HttpClient, HttpClientModule, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { SnackbarComponent } from "./snackbar/snackbar.component";
@NgModule({
  declarations: [
    AppComponent,
    ZoneChangeDetectionComponent,
    SignalChangeDetectionComponent,
    ParentComponent,
    ChildComponent,
    NonSignalComponent,
    SignalComponent,
    RxjsComponent,
    CurrencyComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    SnackbarComponent
],
  providers: [provideHttpClient(withInterceptorsFromDi())],
  bootstrap: [AppComponent]
})
export class AppModule { }
