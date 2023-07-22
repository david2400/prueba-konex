import { Component, OnInit } from '@angular/core';
import {
  DialogService,
  DynamicDialogRef,
  DynamicDialogConfig,
} from 'primeng/dynamicdialog';
import { VentaService } from '@app/services/venta.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css'],
})
export class HistoryComponent {
  constructor(
    public dialogRef: DynamicDialogRef,
    public dialogConfig: DynamicDialogConfig,
    public ventaService: VentaService,
    private messageService: MessageService
  ) {}

  venta = {
    cantidad: 1,
    valorUnitario: 0,
    valorTotal: 0,
    fecha: '',
    medicamento: {
      nombre: '',
      laboratorio: '',
      fechaFabricacion: Date,
      fechaVencimiento: Date,
      cantidadStock: 0,
      valorUnitario: 0,
    },
  };

  ngOnInit(): void {
    this.chargeData();
  }

  onInputChange(event: any) {
    this.venta.cantidad = event.value;

    if (this.venta.cantidad > this.venta.medicamento.cantidadStock) {
      this.venta.cantidad = this.venta.medicamento.cantidadStock;
      this.venta.valorTotal =
        this.venta.medicamento.valorUnitario * this.venta.cantidad;
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'La cantidad no puede ser mayor al stock',
      });

      return;
    }

    this.venta.valorTotal =
      this.venta.medicamento.valorUnitario * this.venta.cantidad;
  }

  async vender() {
    console.log(this.venta.cantidad);
    console.log(this.venta.medicamento.cantidadStock);

    this.venta.fecha = new Date().toISOString();
    console.log(this.venta.fecha);

    await this.ventaService.sell(this.venta).toPromise().then();
    this.dialogRef.close();
    await this.esperarDosSegundos();
    window.location.reload();
  }

  esperarDosSegundos(): Promise<void> {
    return new Promise((resolve) => {
      setTimeout(() => {
        resolve();
      }, 1500);
    });
  }

  chargeData() {
    this.venta.medicamento = this.dialogConfig.data.m;
    this.venta.valorUnitario = this.venta.medicamento.valorUnitario;
    this.venta.valorTotal =
      this.venta.medicamento.valorUnitario * this.venta.cantidad;
    console.log(this.venta);
  }
}
