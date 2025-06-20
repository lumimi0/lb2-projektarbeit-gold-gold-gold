import { Routes } from '@angular/router';

import { CaseOpeningComponent } from './components/case-opening/case-opening.component';
import { InventoryComponent } from './components/inventory/inventory.component';

export const routes: Routes = [
  { path: '', redirectTo: '/cases', pathMatch: 'full' },
  { path: 'cases', component: CaseOpeningComponent },
  { path: 'inventory', component: InventoryComponent },
  { path: '**', redirectTo: '/cases' }
];
