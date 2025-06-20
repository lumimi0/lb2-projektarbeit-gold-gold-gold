import {Component, inject, OnInit} from '@angular/core';
import {CaseService} from '../../services/case.service';
import {AsyncPipe, CurrencyPipe} from '@angular/common';
import {InventoryService} from '../../services/inventory.service';
import {Skin} from '../../models/skin.model';
import {map, Observable} from 'rxjs';

@Component({
  selector: 'app-inventory',
  imports: [
    CurrencyPipe,
    AsyncPipe
  ],
  templateUrl: './inventory.component.html',
  standalone: true,
  styleUrl: './inventory.component.scss'
})
export class InventoryComponent implements OnInit {

  private inventoryService = inject(InventoryService);

  skins: Skin[] = [];

  ngOnInit() {
    this.inventoryService.skins$.subscribe(skins => {
      this.skins = skins;
    });
  }

  getTotalValue(): Observable<string> {
    return this.inventoryService.getTotalInventoryValue().pipe(
      map(data => data.totalValue.toFixed(2))
    );
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
