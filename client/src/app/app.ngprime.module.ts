import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SplitButtonModule } from 'primeng/splitbutton';
import { DynamicDialogModule } from 'primeng/dynamicdialog';
import { DialogModule } from 'primeng/dialog';
import { MessageService } from 'primeng/api';
import { CardModule } from 'primeng/card';
import { CalendarModule } from 'primeng/calendar';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { TabMenuModule } from 'primeng/tabmenu';
import { TableModule } from 'primeng/table';
import { InputNumberModule } from 'primeng/inputnumber';
import { ToastModule } from 'primeng/toast';
@NgModule({
  exports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    SplitButtonModule,
    DynamicDialogModule,
    DialogModule,
    ButtonModule,
    TabMenuModule,
    TableModule,
    CardModule,
    CalendarModule,
    InputTextModule,
    InputNumberModule,
    ToastModule,
    CommonModule,
  ],
  providers: [MessageService],
})
export class NgPrimeModule {}
