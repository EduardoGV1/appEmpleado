import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmpleadosviewComponent } from './pages/empleadosview/empleadosview.component';

const routes:Routes=[
    {
        path:'empleados',
        component:EmpleadosviewComponent
    },
    { path: '**', redirectTo: 'empleados' }
]

@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [
        RouterModule
    ],
})
export class EmpleadosRoutingModule { }
