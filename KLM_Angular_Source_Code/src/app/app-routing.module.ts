import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FareComponent } from './fare/fare.component';
import { AirportComponent } from './airport/airport.component';
import { StatisticsComponent } from './statistics/statistics.component';

const routes: Routes = [
  { path: 'fare-finder', component: FareComponent },
  { path: 'airport-info', component: AirportComponent },
  { path: 'statistics-info', component: StatisticsComponent },
  { path: '', component: FareComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
