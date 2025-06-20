import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { Case } from '../models/case.model';
import { OpenCaseResponse } from '../models/item.model';

@Injectable({
  providedIn: 'root'
})
export class CaseService {
  private apiUrl = 'http://localhost:8080/api/cases';

  constructor(private http: HttpClient) { }

  getAllCases(): Observable<Case[]> {
    return this.http.get<Case[]>(this.apiUrl);
  }

  getCaseById(id: number): Observable<Case> {
    return this.http.get<Case>(`${this.apiUrl}/${id}`);
  }

  openCase(caseId: number): Observable<OpenCaseResponse> {
    return this.http.post<OpenCaseResponse>(`${this.apiUrl}/${caseId}/open`, {});
  }
}
