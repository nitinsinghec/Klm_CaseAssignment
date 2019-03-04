import { Component, OnInit } from '@angular/core';
import { StatisticsService } from './service/statistics.service';
import { Statistics } from './model/statistics';
import { ConstantVariable } from '../common/appconstant';
import * as $ from 'jquery';
declare var jQuery: any;

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html'
})
export class StatisticsComponent implements OnInit {
  errorMsg: string = 'Error';
  statistic: Statistics;
  displayErrorMsg:boolean=false;

  constructor(public statisticsService: StatisticsService) { };

  ngOnInit() {
    
  }

  public chartLabels:string[] = ["2xx Status Count", "4xx Status Count", "5xx Status Count", "Request Count"];
  public pieChartData:number[]= [0, 0, 0, 0];
  public pieChartType:string = 'pie';
  public pieChartOptions:any = {'backgroundColor': ["#FF6384","#4BC0C0","#FFCE56","#E7E9ED"]}
 
    // ADD CHART OPTIONS. 
  chartOptions = {
    responsive: true    // THIS WILL MAKE THE CHART RESPONSIVE (VISIBLE IN ANY DEVICE).
  }
  
  ngAfterViewInit() {
    this.displayErrorMsg=false;
    jQuery('#progressbarContainer').show();
    this.statisticsService.getAppStatistics().subscribe(
      (data) => {
        jQuery('#progressbarContainer').hide();
        this.statistic = data;
        this.pieChartData=[this.statistic.status2xxRequestCount,
          this.statistic.status4xxRequestCount,
          this.statistic.status5xxRequestCount,
          this.statistic.totalRequestCount];
      },
      (err) => {
        jQuery('#progressbarContainer').hide();
        console.log(ConstantVariable.ERROR_MESSAGE);
        this.errorMsg = ConstantVariable.ERROR_MESSAGE;
        this.displayErrorMsg=false;
      });      
  }
}
