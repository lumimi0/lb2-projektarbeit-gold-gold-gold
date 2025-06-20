import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Case} from '../models/case.model';
import {Skin} from '../models/skin.model';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CaseService {

  private http = inject(HttpClient);
  private apiUrl = environment.apiUrl;

  getCases(): Observable<Case[]> {
    return this.http.get<Case[]>(`${this.apiUrl}/api/cases`);
  }

  openCase(caseId: number): Observable<Skin> {
    return this.http.post<Skin>(`${this.apiUrl}/api/cases/${caseId}/open`, {});
  }
}
