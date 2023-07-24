import { Component, EventEmitter, OnInit, Output } from '@angular/core';
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
  id: number = -1;
  text_button: String = 'Register';
  cancel_button: String = 'Cancel';

  formMedication = this.fb.group({
    name: [{ value: '', disabled: false }, Validators.required],
    laboratory: [{ value: '', disabled: false }, Validators.required],
    dateProduction: [
      { value: new Date(), disabled: false },
      Validators.required,
    ],
    dateExpiration: [
      { value: new Date(), disabled: false },
      Validators.required,
    ],
    stock: [
      { value: 1, disabled: false },
      Validators.compose([Validators.required, Validators.min(1)]),
    ],
    unitvalue: [{ value: 0, disabled: false }, Validators.required],
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
      if (this.dialogConfig.data.update) {
        const newMedicamento = {
          ...medicamento,
          id: this.id,
        };
        return await this.medicationService
          .update(newMedicamento)
          .toPromise()
          .then(() => {
            this.dialogRef.close();
            this.messageService.add({
              severity: 'success',
              summary: 'Success',
              detail: 'Modificado Correctamente',
            });
          })
          .catch((data) => {
            this.messageService.add({
              severity: 'error',
              summary: data.error.exceptionName,
              detail: data.error.message,
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
  }

  async ngOnInit(): Promise<any> {
    this.chargeData();
  }

  get f(): { [p: string]: AbstractControl } {
    return this.formMedication.controls;
  }

  chargeData() {
    this.texto_header = this.dialogConfig.data.texto_header;
    this.id = this.dialogConfig.data.m.id;
    this.f['name'].setValue(this.dialogConfig.data.m.name);
    this.f['laboratory'].setValue(this.dialogConfig.data.m.laboratory);
    this.f['dateProduction'].setValue(
      new Date(this.dialogConfig.data.m.dateProduction)
    );
    this.f['dateExpiration'].setValue(
      new Date(this.dialogConfig.data.m.dateExpiration)
    );
    this.f['stock'].setValue(this.dialogConfig.data.m.stock);
    this.f['unitvalue'].setValue(this.dialogConfig.data.m.unitvalue);
    if (this.dialogConfig.data.update) {
      this.isDisabled = true;
      this.text_button = 'Modificar';
    }
    console.log(this.isDisabled);
  }

  validar(campo: string) {
    return this.f[campo].invalid;
  }

  getMensaje(campo: string): string {
    let msg: string = '';

    if (this.f[campo].invalid) {
      msg = 'Por favor diligenciar este campo';
    }
    return msg;
  }

  cancel() {
    this.dialogRef.close();
  }
}
