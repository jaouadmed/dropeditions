import { Component, OnInit } from '@angular/core';
import { MarketstackService } from '../services/marketstack.service';

@Component({
  selector: 'app-section1',
  templateUrl: './section1.component.html',
  styleUrls: ['./section1.component.css']
})
export class Section1Component implements OnInit {

  constructor(private marketstackService: MarketstackService) { }
  
  items = [
    {symbol: "MSFT", close: -1},
    {symbol: "GOOGL", close: -1},
    {symbol: "AMZN", close: -1},
  ]

  ngOnInit() {
    
    this.marketstackService.getClosingPrice("2020-01-03", "MSFT,GOOGL,AMZN").subscribe(response => {
      this.items = response['data'].map(item => { return {symbol: item.symbol, close: item.close}; } );
    }, err => {
      console.log(err);
    });
  }

}
