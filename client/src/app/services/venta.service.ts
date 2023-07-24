import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class VentaService {

  constructor(private http: HttpClient) { }

  getVentas():Observable<any> {
    return this.http.get<any>(`${environment.API_URL}/api/sales/`).pipe();
  }

  sell(venta: any): Observable<Object> {
    console.log(venta);
    return this.http.post(`${environment.API_URL}/api/sales/register`, venta);
  }
}
