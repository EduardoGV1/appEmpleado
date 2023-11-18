import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EmpleadosviewComponent } from './pages/empleadosview/empleadosview.component';
import { EmpleadosRoutingModule } from './empleados-routing.module';
import { SharedModule } from '../shared/shared.module';
import { CrearempleadoviewComponent } from './pages/crearempleadoview/crearempleadoview.component';
import { VerempleadoviewComponent } from './pages/verempleadoview/verempleadoview.component';



@NgModule({
  declarations: [
    EmpleadosviewComponent,
    CrearempleadoviewComponent,
    VerempleadoviewComponent
  ],
  imports: [
    CommonModule,
    EmpleadosRoutingModule,
    SharedModule
  ]
})
export class EmpleadosModule { }
