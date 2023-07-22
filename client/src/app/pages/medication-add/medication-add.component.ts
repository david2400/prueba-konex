import { Component, OnInit } from '@angular/core';
import { MedicamentoService } from '@app/services/medicamento.service';
import { MessageService } from 'primeng/api';
import {
  DialogService,
  DynamicDialogRef,
  DynamicDialogConfig,
} from 'primeng/dynamicdialog';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-medication-add',
  templateUrl: './medication-add.component.html',
  styleUrls: ['./medication-add.component.css'],
})
export class MedicationAddComponent {
  isDisabled = false;
  texto_header: String = '';
  text_button: String = 'Register';
  cancel_button: String = 'Cancel';

  formMedication = this.fb.group({
    nombre: [{ value: '', disabled: false }, Validators.required],
    laboratorio: [{ value: '', disabled: false }, Validators.required],
    fechaFabricacion: [
      { value: new Date(), disabled: false },
      Validators.required,
    ],
    fechaVencimiento: [
      { value: new Date(), disabled: false },
      Validators.required,
    ],
    cantidadStock: [
      { value: 1, disabled: false },
      Validators.compose([Validators.required, Validators.min(1)]),
    ],
    valorUnitario: [{ value: 0, disabled: false }, Validators.required],
  });

  constructor(
    public fb: FormBuilder,
    private medicationService: MedicamentoService,
    public dialogRef: DynamicDialogRef,
    public dialogConfig: DynamicDialogConfig,
    private messageService: MessageService
  ) {}

  async submitForm() {
    if (this.formMedication.valid) {
      const medicamento = this.formMedication.value;
      if (this.texto_header === 'Modificar Medicamento') {
        return await this.medicationService
          .update(medicamento)
          .toPromise()
          .then(() => {
            this.dialogRef.close();
            this.messageService.add({
              severity: 'success',
              summary: 'Success',
              detail: 'Modificado Correctamente',
            });
          });
      } else {
        return await this.medicationService
          .create(medicamento)
          .toPromise()
          .then(() => {
            this.dialogRef.close();
            this.messageService.add({
              severity: 'success',
              summary: 'Success',
              detail: 'Agregado Correctamente',
            });
          });
      }
    }
  }

  async ngOnInit(): Promise<any> {
    this.chargeData();
  }

  get f(): { [p: string]: AbstractControl } {
    return this.formMedication.controls;
  }

  chargeData() {
    this.texto_header = this.dialogConfig.data.texto_header;

    this.f['nombre'].setValue(this.dialogConfig.data.m.nombre);
    this.f['laboratorio'].setValue(this.dialogConfig.data.m.laboratorio);
    this.f['fechaFabricacion'].setValue(
      new Date(this.dialogConfig.data.m.fechaFabricacion)
    );
    this.f['fechaVencimiento'].setValue(
      new Date(this.dialogConfig.data.m.fechaVencimiento)
    );
    this.f['cantidadStock'].setValue(this.dialogConfig.data.m.cantidadStock);
    this.f['valorUnitario'].setValue(this.dialogConfig.data.m.valorUnitario);

    if (this.texto_header === 'Modificar Medicamento') {
      this.isDisabled = true;
      this.text_button = 'Modificar';
    }
  }

  cancel() {
    this.dialogRef.close();
  }
}
