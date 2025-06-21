import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CaseService } from '../../services/case.service';
import { Case } from '../../models/case.model';
import { OpenCaseResponse, Rarity } from '../../models/item.model';

@Component({
  selector: 'app-case-opening',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './case-opening.component.html',
  styleUrls: ['./case-opening.component.scss']
})
export class CaseOpeningComponent implements OnInit {
  cases: Case[] = [];
  selectedCase: Case | null = null;
  lastOpenedItem: OpenCaseResponse | null = null;
  isOpening = false;
  loading = true;

  constructor(private caseService: CaseService) { }

  ngOnInit(): void {
    this.loadCases();
  }

  loadCases(): void {
    this.caseService.getAllCases().subscribe({
      next: (cases) => {
        this.cases = cases;
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading cases:', error);
        this.loading = false;
      }
    });
  }

  selectCase(caseItem: Case): void {
    this.selectedCase = caseItem;
    this.lastOpenedItem = null;
  }

  openCase(): void {
    if (!this.selectedCase || this.isOpening) return;

    this.isOpening = true;
    this.caseService.openCase(this.selectedCase.id).subscribe({
      next: (result) => {
        setTimeout(() => {
          this.lastOpenedItem = result;
          this.isOpening = false;
        }, 1500);
      },
      error: (error) => {
        console.error('Error opening case:', error);
        this.isOpening = false;
      }
    });
  }

  getRarityClass(rarity: Rarity): string {
    return `rarity-${rarity.toLowerCase()}`;
  }

  getRarityDisplayName(rarity: Rarity): string {
    switch(rarity) {
      case Rarity.GEWÖHNLICH: return 'Gewöhnlich';
      case Rarity.SELTEN: return 'Selten';
      case Rarity.EPISCH: return 'Episch';
      case Rarity.LEGENDÄR: return 'Legendär';
      default: return rarity;
    }
  }
}
