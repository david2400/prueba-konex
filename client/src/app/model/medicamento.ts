export interface Medicamento {
  estado?:number;
  nombre?: string;
  laboratorio?: string;
  fechaFabricacion?: Date;
  fechaVencimiento?: Date;
  cantidadStock?: number;
  valorUnitario?: number;
}
