import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MedicamentoService {

  constructor(private http: HttpClient) { }

  getMedicamentos():Observable<any> {
    return this.http.get<any>(`${environment.API_URL}/api/medicamentos/`).pipe();
  }

  getMedicamento(nombre:String,laboratorio:String):Observable<any> {
    return this.http.get<any>(`${environment.API_URL}/api/medicamentos/${nombre}/${laboratorio}`).pipe();
  }

  create(medication: any): Observable<Object> {
    return this.http.post(`${environment.API_URL}/api/medicamentos/create`, medication);
  }

  update(medication: any): Observable<Object> {
    return this.http.put(`${environment.API_URL}/api/medicamentos/update`, medication);
  }

  delete(nombre:String,laboratorio:String) {
    return this.http.delete(`${environment.API_URL}/api/medicamentos/${nombre}/${laboratorio}`);
  }
}
