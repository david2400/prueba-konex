import { Component, OnInit } from '@angular/core';
import {
  DialogService,
  DynamicDialogRef,
  DynamicDialogConfig,
} from 'primeng/dynamicdialog';
import { VentaService } from '@app/services/venta.service';
import { MessageService } from 'primeng/api';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css'],
})
export class HistoryComponent {
  formSale = this.fb.group({
    quantity: [
      { value: 1, disabled: false },
      Validators.compose([Validators.required, Validators.min(1)]),
    ],
    unitvalue: [{ value: 0, disabled: false }, Validators.required],
    total: [
      { value: 1, disabled: false },
      Validators.compose([Validators.required]),
    ],
    createdate: [{ value: new Date(), disabled: false }, Validators.required],
  });

  constructor(
    public fb: FormBuilder,
    public dialogRef: DynamicDialogRef,
    public dialogConfig: DynamicDialogConfig,
    public ventaService: VentaService,
    private messageService: MessageService
  ) {}

  medication = {
    name: '',
    laboratory: '',
    fechaFabricacion: Date,
    fechaVencimiento: Date,
    stock: 0,
    valorUnitario: 0,
  };

  ngOnInit(): void {
    this.chargeData();
  }

  onInputChange() {
    if (this.f['quantity'].value > this.medication.stock) {
      this.f['quantity'].setValue(this.medication.stock);

      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'La cantidad no puede ser mayor al stock',
      });
    }

    this.f['total'].setValue(
      this.f['unitvalue'].value * this.f['quantity'].value
    );
  }

  async vender() {
    if (this.formSale.valid) {
      const venta = {
        ...this.formSale.value,
        medication: this.medication,
      };
      await this.ventaService
        .sell(venta)
        .toPromise()
        .then((data) => {
          this.messageService.add({
            severity: 'success',
            summary: 'Success',
            detail: 'Venta exitosa!',
          });
          this.dialogRef.close();
        })
        .catch((data) => {
          this.messageService.add({
            severity: 'error',
            summary: data.error.exceptionName,
            detail: data.error.message,
          });
        });
    }
  }

  get f(): { [p: string]: AbstractControl } {
    return this.formSale.controls;
  }

  chargeData() {
    this.medication = this.dialogConfig.data.m;
    this.f['quantity'].addValidators(
      Validators.maxLength(this.dialogConfig.data.m.stock)
    );
    this.f['unitvalue'].setValue(this.dialogConfig.data.m.unitvalue);
    this.f['total'].setValue(
      this.dialogConfig.data.m.unitvalue * this.f['quantity'].value
    );
    console.log(this.dialogConfig.data.m);
  }
}
