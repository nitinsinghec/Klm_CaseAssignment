import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { ChartsModule } from 'ng2-charts';


import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { FareComponent } from './fare/fare.component';
import { AirportComponent } from './airport/airport.component';
import { StatisticsComponent } from './statistics/statistics.component';
import { AirportService } from './airport/service/airport.service';
import { FareService } from './fare/service/fare.service';
import { StatisticsService } from './statistics/service/statistics.service';
import { HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './header/header.component';
import { DataTablesModule } from 'angular-datatables';

@NgModule({
  declarations: [
    AppComponent,
    FareComponent,
    AirportComponent,
    StatisticsComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    ChartsModule,
    DataTablesModule,
    BrowserAnimationsModule
  ],
  providers: [AirportService,FareService,StatisticsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
