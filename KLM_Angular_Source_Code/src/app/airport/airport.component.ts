import { Component, OnInit } from '@angular/core';
import { DataTablesModule } from 'angular-datatables';
import * as $ from 'jquery';

import { AirportService } from './service/airport.service';
import { Airport } from './model/airport';
import { AirportInfo } from './model/airportInfo';
import { ConstantVariable } from '../common/appconstant';
declare var jQuery: any;
@Component({
  selector: 'app-airport',
  templateUrl: './airport.component.html'
})
export class AirportComponent implements OnInit {

  constructor(public airportService: AirportService) { };

  airports: Array<Airport>;
  airportsInfo: Array<AirportInfo>;
  airportDetail:AirportInfo;
  errorMsg: string = '';
  displayErrorMsg:boolean=false;

  ngOnInit() {
    
  }

  ngAfterViewInit() {
    this.getDetailedListOfAirports();
  }

  getListOfAirports(){
    this.displayErrorMsg=false;
    jQuery('#progressbarContainer').show();
    this.airportService.getAirports().subscribe(
      (data) => {
        jQuery('#progressbarContainer').hide();
        this.airports=data._embedded.locations;
      },
      (err) => {
        jQuery('#progressbarContainer').hide();
        console.log(ConstantVariable.ERROR_MESSAGE);
        this.errorMsg = ConstantVariable.ERROR_MESSAGE;
      });

  }

  getDetailedListOfAirports(){
    this.displayErrorMsg=false;
    jQuery('#progressbarContainer').show();
    this.airportService.getAirportInfoList().subscribe(
      (data) => {
        jQuery('#progressbarContainer').hide();
        this.airportsInfo=data._embedded.locations;
      },
      (err) => {
        jQuery('#progressbarContainer').hide();
        console.log(ConstantVariable.ERROR_MESSAGE);
        this.errorMsg = ConstantVariable.ERROR_MESSAGE;
        this.displayErrorMsg=true;
      });
  }

  getAirportDetailsByCode(airportCode){
    jQuery('#progressbarContainer').show();
    this.airportService.getAirportByCode(airportCode).subscribe(
      (data) => {
        jQuery('#progressbarContainer').hide();
        this.airportDetail=data;
      },
      (err) => {
        jQuery('#progressbarContainer').hide();
        console.log(ConstantVariable.ERROR_MESSAGE);
        this.errorMsg = ConstantVariable.ERROR_MESSAGE;
        this.displayErrorMsg=true;
      });
  }

}
