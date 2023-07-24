import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class MedicamentoService {
  constructor(private http: HttpClient) {}

  create(medication: any): Observable<Object> {
    return this.http.post(
      `${environment.API_URL}/api/medication/register`,
      medication
    );
  }

  delete(id: Number) {
    return this.http
      .delete(`${environment.API_URL}/api/medication/delete/${id}`)
      .pipe();
  }
  getMedicamentos(): Observable<any> {
    return this.http.get<any>(`${environment.API_URL}/api/medication/`).pipe();
  }

  getMedicamento(id: number): Observable<any> {
    return this.http
      .get<any>(`${environment.API_URL}/api/medication/${id}`)
      .pipe();
  }

  update(medication: any): Observable<Object> {
    return this.http.put(
      `${environment.API_URL}/api/medication/update`,
      medication
    );
  }
}
