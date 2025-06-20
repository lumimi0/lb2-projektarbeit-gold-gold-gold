import {Component, EventEmitter, inject, Input, OnChanges, Output} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CaseService} from '../../services/case.service';
import {Case} from '../../models/case.model';
import {Skin} from '../../models/skin.model';
import {CurrencyPipe} from '@angular/common';
import {InventoryService} from '../../services/inventory.service';

@Component({
  selector: 'app-case-opening',
  imports: [
    CurrencyPipe
  ],
  templateUrl: './case-opening.component.html',
  standalone: true,
  styleUrl: './case-opening.component.scss'
})
export class CaseOpeningComponent implements OnChanges {

  @Input() selectedCase: Case | null = null;
  @Output() caseOpened = new EventEmitter<void>();
  @Output() modalClosed = new EventEmitter<void>();

  private caseService = inject(CaseService);
  private inventoryService = inject(InventoryService);

  showModal = false;
  opening = false;
  receivedSkin: Skin | null = null;

  ngOnChanges() {
    if (this.selectedCase) {
      this.showModal = true;
      this.opening = false;
      this.receivedSkin = null;
    }
  }

  openCase() {
    if (!this.selectedCase) return;

    this.opening = true;

    // Simuliere Case Opening mit Delay
    setTimeout(() => {
      this.caseService.openCase(this.selectedCase!.id).subscribe(skin => {
        this.receivedSkin = skin;
        this.opening = false;

        // Geld abziehen und Skin zum Inventar hinzufügen
        this.inventoryService.deductBalance(this.selectedCase!.price);
        this.inventoryService.addSkin(skin);

        this.caseOpened.emit();
      });
    }, 2000);
  }

  closeModal() {
    this.showModal = false;
    this.receivedSkin = null;
    this.opening = false;
    this.modalClosed.emit();
  }

  getRarityColor(rarity: string): string {
    const colors: { [key: string]: string } = {
      'Consumer': 'secondary',
      'Industrial': 'info',
      'Mil-Spec': 'primary',
      'Restricted': 'warning',
      'Classified': 'danger',
      'Covert': 'success',
      'Exceedingly Rare': 'dark'
    };
    return colors[rarity] || 'secondary';
  }
}
