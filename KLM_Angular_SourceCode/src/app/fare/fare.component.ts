import { Component, OnInit } from '@angular/core';
import {FormBuilder,FormGroup,FormControl,Validators,NgForm} from '@angular/forms'  
import * as $ from 'jquery';
import { FareService } from './service/fare.service';
import { AirportService } from '../airport/service/airport.service';
import { Airport } from '../airport/model/airport';
import { Fare } from './model/fare';
import { ConstantVariable } from '../common/appconstant';
declare var jQuery: any;

@Component({
  selector: 'app-fare',
  templateUrl: './fare.component.html'
})
export class FareComponent implements OnInit {
 
  searchObjects:Airport[];
  searchDObjects:Airport[];
  airports: Array<Airport>;
  errorMsg: String = '';
  fare: Fare;
  origin: String = '';
  destination: String = '';
  selectedCurrency: String = 'EUR';  
  displayErrorMsg:boolean=false;
  selectedSource: String = '';
  selectedDestination: String = '';
  tempCode: String = '';

  constructor(public fareService: FareService,public airportService: AirportService) {}

  ngOnInit() {
  }

  ngAfterViewInit() {   
    this.loadAirportData();
  }

  getOriginFilteredAirportList($event) {
    this.searchObjects=[];
    if (this.origin.length > 0) {
      this.searchObjects = this.searchFromArray(this.airports, this.origin);
      this.searchObjects=this.airports;
     }
  } 
  
  getDestinationFilteredAirportList($event) {
    this.searchDObjects=[];
    if (this.destination.length > 0) {
      this.searchDObjects = this.searchFromArray(this.airports, this.destination);
      this.searchDObjects=this.airports;
    }
  }

 searchFromArray(arr, searchString:String) {
    let matches = [], i=0;
    try{
      for (i = 0; i < arr.length; i++) {
        if (arr[i].contains(searchString)) {
          matches.push(arr[i]);
        }
      }
    }catch(Exception){}
    return matches;
  };

  loadAirportData(){
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
        this.displayErrorMsg=true;
      });
  }

  findFare(){
    this.displayErrorMsg=false;
    jQuery('#progressbarContainer').show();
    this.fareService.getFare(this.selectedSource,this.selectedDestination,this.selectedCurrency).subscribe(
      (data) => {
        jQuery('#progressbarContainer').hide();
        this.fare = data;
      },
      (err) => {
        jQuery('#progressbarContainer').hide();
        console.log(ConstantVariable.ERROR_MESSAGE);
        this.errorMsg = ConstantVariable.ERROR_MESSAGE;
        this.displayErrorMsg=true;
      }
    );
  }

  validateSourceDestination(){
    this.selectedSource=this.getAirportCode(this.origin);
    this.selectedDestination=this.getAirportCode(this.destination)
    console.log("Search Flight");
    console.log("Origin --"+this.selectedSource);
    console.log("Destination --"+this.selectedDestination);
    if(this.selectedSource===''){
      this.errorMsg = ConstantVariable.ERROR_MESSAGE_INVALID_ORIGIN;
      this.displayErrorMsg=true;
      return false;
    }
    if(this.selectedDestination===''){
      this.errorMsg = ConstantVariable.ERROR_MESSAGE_INVALID_DESTINATION;
      this.displayErrorMsg=true;
      return false;
    }
    if(this.selectedDestination===this.selectedSource){
      this.errorMsg = ConstantVariable.ERROR_MESSAGE_SAME_ORIGIN_DESTINATION;
      this.displayErrorMsg=true;
      return false;
    }
    return true;
  }

  getAirportCode(content):String{
    this.tempCode='';
    this.airports.forEach(airport => {
      if(airport.code.toLowerCase()===content.toLowerCase().trim()){
        this.tempCode=airport.code;
      }else if(airport.name.toLowerCase()===content.toLowerCase().trim()){
        this.tempCode=airport.code;
      }
    });
    return this.tempCode;
  }

  search(){
    this.displayErrorMsg=false;
    if(this.validateSourceDestination()){
      this.findFare();
    }
  }
}
