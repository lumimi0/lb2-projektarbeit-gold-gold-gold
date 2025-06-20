import { Routes } from '@angular/router';

import {CaseListComponent} from './pages/case-list/case-list.component';
import {CaseOpeningComponent} from './pages/case-opening/case-opening.component';
import {InventoryComponent} from './pages/inventory/inventory.component';

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/cases' },
  { path: 'cases', component: CaseListComponent },
  { path: 'case/:id', component: CaseOpeningComponent },
  { path: 'inventory', component: InventoryComponent }
];
