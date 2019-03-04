import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Airport } from '../model/airport';
import { AirportInfo } from '../model/airportInfo';
import { environment } from '../../../environments/environment';
import { AirportWrapper } from '../model/airportWrapper';
import { AirportDetailWrapper } from '../model/airportDetailWrapper';

@Injectable({
  providedIn: 'root'
})
export class AirportService {

  constructor(private http:HttpClient){}
  
  public getAirports() {
    return this.http.get<AirportWrapper>(environment.BASE_API_URL+"airports");
  }

  public getAirportInfoList() {
    return this.http.get<AirportDetailWrapper>(environment.BASE_API_URL+"airportsInfo");
  }

  public getAirportByCode(code: String) {
    return this.http.get<AirportInfo>(environment.BASE_API_URL+"airportDetail/"+code);
  }

}
