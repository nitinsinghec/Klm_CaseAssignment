import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Fare } from '../../fare/model/fare';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FareService {

  constructor(private http:HttpClient){}

  public getFare(originCode: String, destinationCode: String, selectedCurrency: String) {
    return this.http.get<Fare>(environment.BASE_API_URL+"fares/" + originCode + "/" + destinationCode+"?currency="+selectedCurrency);
  }
}
