import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {Skin} from '../models/skin.model';
import {environment} from '../../environments/environment';

export interface InventoryItem {
  id: number;
  skin: Skin;
  acquiredAt: string;
}

@Injectable({
  providedIn: 'root'
})
export class InventoryService {

  private http = inject(HttpClient);
  private apiUrl = environment.apiUrl;

  private skinsSubject = new BehaviorSubject<Skin[]>([]);
  private balanceSubject = new BehaviorSubject<number>(100);

  skins$ = this.skinsSubject.asObservable();
  balance$ = this.balanceSubject.asObservable();

  loadInventory(): Observable<InventoryItem[]> {
    return this.http.get<InventoryItem[]>(`${this.apiUrl}/api/inventory`);
  }

  loadBalance(): Observable<{balance: number}> {
    return this.http.get<{balance: number}>(`${this.apiUrl}/api/inventory/balance`);
  }

  getTotalInventoryValue(): Observable<{totalValue: number}> {
    return this.http.get<{totalValue: number}>(`${this.apiUrl}/api/inventory/value`);
  }

  // Lokale State-Management Methoden für Kompatibilität
  updateLocalInventory() {
    this.loadInventory().subscribe(inventoryItems => {
      const skins = inventoryItems.map(item => item.skin);
      this.skinsSubject.next(skins);
    });
  }

  updateLocalBalance() {
    this.loadBalance().subscribe(response => {
      this.balanceSubject.next(response.balance);
    });
  }

  // Legacy-Methoden für Kompatibilität mit bestehendem Code
  addSkin(skin: Skin) {
    // Wird jetzt über Backend gehandhabt, aber wir aktualisieren den lokalen State
    this.updateLocalInventory();
  }

  getSkins(): Skin[] {
    return this.skinsSubject.value;
  }

  getBalance(): number {
    return this.balanceSubject.value;
  }

  deductBalance(amount: number) {
    // Wird jetzt über Backend gehandhabt, aber wir aktualisieren den lokalen State
    this.updateLocalBalance();
  }

  getTotalInventoryValueLocal(): number {
    return this.skinsSubject.value.reduce((total, skin) => total + skin.price, 0);
  }
}
