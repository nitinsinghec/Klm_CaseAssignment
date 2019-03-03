import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Statistics } from '../../statistics/model/statistics';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StatisticsService {

  constructor(private http:HttpClient){}

  public getAppStatistics() {
    return this.http.get<Statistics>(environment.BASE_API_URL+"statistics");
  }
}
