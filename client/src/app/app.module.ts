import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from '@app/app-routing.module';
import { AppComponent } from '@app/app.component';
import { NgPrimeModule } from '@app/app.ngprime.module';
import { NavbarComponent } from '@app/components/navbar/navbar.component';
import { FooterComponent } from '@app/components/footer/footer.component';
import { MedicationAddComponent } from '@pages/medication-add/medication-add.component';
import { MedicationComponent } from '@pages/medication/medication.component';
import { SalesComponent } from '@pages/sales/sales.component';
import { HistoryComponent } from '@pages/history/history.component';
import { MedicamentoService } from '@app/services/medicamento.service';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    NavbarComponent,
    AppComponent,
    MedicationAddComponent,
    MedicationComponent,
    SalesComponent,
    HistoryComponent,
    FooterComponent,
  ],
  imports: [BrowserModule, NgPrimeModule, AppRoutingModule, HttpClientModule],
  providers: [MedicamentoService],
  bootstrap: [AppComponent],
})
export class AppModule {}
