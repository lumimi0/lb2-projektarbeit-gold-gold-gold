import { Item } from './item.model';

export interface Case {
  id: number;
  name: string;
  imageUrl: string;
  possibleItems: Item[];
}
