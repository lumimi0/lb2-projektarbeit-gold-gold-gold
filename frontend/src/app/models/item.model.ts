export interface Item {
  id: number;
  name: string;
  rarity: Rarity;
  value: number;
  imageUrl: string;
}

export enum Rarity {
  GEWÖHNLICH = 'GEWÖHNLICH',
  SELTEN = 'SELTEN',
  EPISCH = 'EPISCH',
  LEGENDÄR = 'LEGENDÄR'
}

export interface OpenCaseResponse {
  itemId: number;
  itemName: string;
  rarity: Rarity;
  value: number;
  imageUrl: string;
}
