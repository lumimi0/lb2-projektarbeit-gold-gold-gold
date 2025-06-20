import {Component, inject} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {HeaderComponent} from './layout/header/header.component';
import {CaseOpeningComponent} from './pages/case-opening/case-opening.component';
import {InventoryComponent} from './pages/inventory/inventory.component';
import {CaseListComponent} from './pages/case-list/case-list.component';
import {InventoryService} from './services/inventory.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent, CaseOpeningComponent, InventoryComponent, CaseListComponent],
  templateUrl: './app.component.html',
  standalone: true,
  styleUrl: './app.component.scss'
})
export class AppComponent {

  private inventoryService = inject(InventoryService);

  balance = 0;
  selectedCase: any = null;

  ngOnInit() {
    this.inventoryService.balance$.subscribe(balance => {
      this.balance = parseFloat(balance.toFixed(2));
    });
  }

  onCaseSelected(caseItem: any) {
    if (this.inventoryService.getBalance() >= caseItem.price) {
      this.selectedCase = caseItem;
    } else {
      alert('Nicht genug Geld!');
    }
  }

  onCaseOpened() {
    this.selectedCase = null;
  }

  onModalClosed() {
    this.selectedCase = null;
  }
}
