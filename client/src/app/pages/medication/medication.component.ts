import { Component } from '@angular/core';
import { MedicamentoService } from '@app/services/medicamento.service';
import { MenuItem, MessageService } from 'primeng/api';
import { DialogService, DynamicDialogRef } from 'primeng/dynamicdialog';
import { Table } from 'primeng/table';
import { MedicationAddComponent } from '@pages/medication-add/medication-add.component';
import { HistoryComponent } from '@pages/history/history.component';
import { Medicamento } from '@app/model/medicamento';

@Component({
  selector: 'app-medication',
  templateUrl: './medication.component.html',
  styleUrls: ['./medication.component.css'],
  providers: [DialogService, MessageService],
})
export class MedicationComponent {
  constructor(
    private medicantoService: MedicamentoService,
    public dialogService: DialogService,
    public messageService: MessageService
  ) {}

  ngOnInit() {
    this.getMedicamentos();
  }

  ref: DynamicDialogRef = new DynamicDialogRef();
  id: number = -1;
  date: Date = new Date();
  medicacion: Medicamento[] = [];

  items: MenuItem[] = [
    {
      label: 'Update',
      icon: 'pi pi-pencil',
      command: () => {
        this.showUpdateMedication();
      },
    },
    { separator: true },
    {
      label: 'Delete',
      icon: 'pi pi-trash',
      command: () => {
        this.delet();
      },
      expanded: true,
    },
  ];

  async getMedicamentos() {
    await this.medicantoService
      .getMedicamentos()
      .toPromise()
      .then((data) => (this.medicacion = data));
  }

  handleClick(id: number) {
    this.id = id;
  }

  async delet() {
    await this.medicantoService
      .delete(this.id)
      .toPromise()
      .then((data) => {
        this.messageService.add({
          severity: 'success',
          summary: 'Success',
          detail: 'Se ha eliminado correctamente',
        });
        this.getMedicamentos();
      })
      .catch((data) => {
        this.messageService.add({
          severity: 'error',
          summary: data.error.exceptionName,
          detail: data.error.message,
        });
      });
  }

  async sell(medicamento: any) {
    const ref: DynamicDialogRef = this.dialogService.open(HistoryComponent, {
      header: 'Confirmar cantidad',
      width: '70%',
      data: {
        m: medicamento,
      },
      contentStyle: { overflow: 'auto' },
      baseZIndex: 10000,
      maximizable: true,
    });
    ref.onClose.subscribe(() => {
      this.getMedicamentos();
    });
  }

  clear(table: Table) {
    table.clear();
  }

  ngOnDestroy() {
    if (this.ref) {
      this.ref.close();
    }
  }

  showModalMedication(update: Boolean, medicamento: any = null) {
    const ref: DynamicDialogRef = this.dialogService.open(
      MedicationAddComponent,
      {
        header: update ? 'Modificar Medicamento' : 'Registrar Medicamento',
        width: '70%',
        data: {
          m: medicamento,
          texto_header: update
            ? 'Modificar Medicamento'
            : 'Registrar Medicamento',
          update: update,
        },
        contentStyle: { overflow: 'auto' },
        baseZIndex: 10000,
        maximizable: true,
      }
    );
    ref.onClose.subscribe(() => {
      this.getMedicamentos();
    });
  }

  async showUpdateMedication() {
    await this.medicantoService
      .getMedicamento(this.id)
      .toPromise()
      .then((data) => {
        this.showModalMedication(true, data);
      });
  }
}
