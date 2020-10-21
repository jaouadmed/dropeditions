import { Component, OnInit } from '@angular/core';
import { MarketstackService } from '../services/marketstack.service';

@Component({
  selector: 'app-section2',
  templateUrl: './section2.component.html',
  styleUrls: ['./section2.component.css']
})
export class Section2Component implements OnInit {
  
  constructor(private marketstackService: MarketstackService) { }
  
  items = [
    {symbol: "MSFT", close: -1},
    {symbol: "GOOGL", close: -1},
    {symbol: "AMZN", close: -1},
  ]
  
  ngOnInit() {
    
    this.marketstackService.getClosingPrice("2020-01-04", "MSFT,GOOGL,AMZN").subscribe(response => {
      this.items = response['data'].map(item => { return {symbol: item.symbol, close: item.close}; } );
      console.log(this.items);
    }, err => {
      console.log(err);
    });
  }

}
