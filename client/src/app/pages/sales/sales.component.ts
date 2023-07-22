import { Component, OnInit } from '@angular/core';
import { VentaService } from '@app/services/venta.service';
import { Table } from 'primeng/table';
import { Venta } from '@app/model/venta';

@Component({
  selector: 'app-sales',
  templateUrl: './sales.component.html',
  styleUrls: ['./sales.component.css'],
})
export class SalesComponent {
  constructor(private ventaService: VentaService) {}

  ngOnInit() {
    this.getVentas();
  }

  ventas: Venta[] = [];
  ventasFiltradas: Venta[] = [];
  fabricacionFilter: Date = new Date();
  vencimientoFilter: Date = new Date();

  async getVentas() {
    await this.ventaService
      .getVentas()
      .toPromise()
      .then((data) => {
        this.ventas = data;
        this.ventasFiltradas = data;
      });
  }

  clear(table: Table) {
    this.ventasFiltradas = this.ventas;
    table.clear();
  }

  filtrarPorFechas() {
    let arr = this.ventas;
    console.log(this.vencimientoFilter);

    this.ventasFiltradas = arr.filter((element) => {
      const fecha = new Date(element.fecha!);
      let x = new Date(this.fabricacionFilter);
      let y = new Date(this.vencimientoFilter);

      if (fecha < y) {
        console.log('entro');
      }

      return fecha >= x && fecha <= y;
    });
    console.log(this.ventasFiltradas);
  }
}
