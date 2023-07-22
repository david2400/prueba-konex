import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MedicationComponent } from '@pages/medication/medication.component';
import { MedicationAddComponent } from '@pages/medication-add/medication-add.component';
import { SalesComponent } from '@pages/sales/sales.component';

export const routes: Routes = [
  {
    path: 'medication',
    component: MedicationComponent,
  },
  {
    path: 'addMedication',
    component: MedicationAddComponent,
  },

  {
    path: 'sales',
    component: SalesComponent,
  },

  { path: '**', pathMatch: 'full', redirectTo: 'medication' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
