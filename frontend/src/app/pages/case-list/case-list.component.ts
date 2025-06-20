import {Component, EventEmitter, inject, OnInit, Output} from '@angular/core';
import {Case} from '../../models/case.model';
import {CaseService} from '../../services/case.service';
import {AsyncPipe, CurrencyPipe} from '@angular/common';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-case-list',
  imports: [
    AsyncPipe,
    CurrencyPipe,
    RouterLink
  ],
  templateUrl: './case-list.component.html',
  standalone: true,
  styleUrl: './case-list.component.scss'
})
export class CaseListComponent /*implements OnInit*/ {

  @Output() caseSelected = new EventEmitter<Case>();

  private caseService = inject(CaseService);

  cases: Case[] = [];

  ngOnInit() {
    this.caseService.getCases().subscribe(cases => {
      this.cases = cases;
    });
  }

  selectCase(caseItem: Case) {
    this.caseSelected.emit(caseItem);
  }
}
