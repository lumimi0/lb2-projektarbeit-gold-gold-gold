import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InventoryService } from '../../services/inventory.service';
import { InventoryItem, InventoryValue } from '../../models/inventory.model';
import { Rarity } from '../../models/item.model';

@Component({
  selector: 'app-inventory',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.scss']
})
export class InventoryComponent implements OnInit {
  inventoryItems: InventoryItem[] = [];
  inventoryValue: InventoryValue = { totalValue: 0, itemCount: 0 };
  loading = true;

  constructor(private inventoryService: InventoryService) { }

  ngOnInit(): void {
    this.loadInventory();
    this.loadInventoryValue();
  }

  loadInventory(): void {
    this.inventoryService.getInventory().subscribe({
      next: (items) => {
        this.inventoryItems = items;
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading inventory:', error);
        this.loading = false;
      }
    });
  }

  loadInventoryValue(): void {
    this.inventoryService.getInventoryValue().subscribe({
      next: (value) => {
        this.inventoryValue = value;
      },
      error: (error) => {
        console.error('Error loading inventory value:', error);
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

  formatDate(dateString: string): string {
    const date = new Date(dateString);
    return date.toLocaleDateString('de-CH') + ' ' + date.toLocaleTimeString('de-CH', {
      hour: '2-digit',
      minute: '2-digit'
    });
  }
}
