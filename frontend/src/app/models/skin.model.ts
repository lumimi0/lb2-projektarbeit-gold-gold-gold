export interface Skin {
  id: number;
  name: string;
  rarity: 'Consumer' | 'Industrial' | 'Mil-Spec' | 'Restricted' | 'Classified' | 'Covert' | 'Exceedingly Rare';
  price: number;
  imageUrl: string;
  caseId: number;
}
