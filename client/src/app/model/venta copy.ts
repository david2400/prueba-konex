import { Medicamento } from "./medicamento";

export interface Venta {
    cantidad?: number,
    valorUnitario?: number,
    valorTotal?: number,
    fecha?: Date,
    medicamento?: Medicamento
  }