import { Component, OnInit, Input } from '@angular/core';
import { BumService } from '../services/bum.service';
import { BusinessUnitManager } from '../definitions/business-unit-manager';

@Component({
  selector: 'app-bum-selector',
  templateUrl: './bum-selector.component.html',
  styleUrls: ['./bum-selector.component.scss']
})
export class BumSelectorComponent implements OnInit {

  constructor(private bumService: BumService) { }

  bums: BusinessUnitManager[] = [];

  @Input()
  selected: BusinessUnitManager;

  ngOnInit() {
    this.loadBUMs();
  }

  async loadBUMs() {
    this.bums = await this.bumService.getBUMs().toPromise();
  }
}
