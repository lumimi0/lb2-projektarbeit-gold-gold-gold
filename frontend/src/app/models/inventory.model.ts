import { Rarity } from './item.model';

export interface InventoryItem {
  inventoryId: number;
  itemName: string;
  rarity: Rarity;
  value: number;
  imageUrl: string;
  obtainedAt: string;
}

export interface InventoryValue {
  totalValue: number;
  itemCount: number;
}
