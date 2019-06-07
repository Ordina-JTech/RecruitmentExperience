import { Component, OnInit, Input } from '@angular/core';
import { BumService } from '../services/bum.service';
import { BUM } from '../interfaces/bum';

@Component({
  selector: 'app-bum-selector',
  templateUrl: './bum-selector.component.html',
  styleUrls: ['./bum-selector.component.scss']
})
export class BumSelectorComponent implements OnInit {

  constructor(private bumService: BumService) { }

  bums: BUM[] = [];

  @Input()
  selected: BUM;

  ngOnInit() {
    this.loadBUMs();
  }

  async loadBUMs() {
    this.bums = await this.bumService.getBUMs();
  }
}
