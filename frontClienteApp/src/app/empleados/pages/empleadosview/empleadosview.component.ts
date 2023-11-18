import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { EmpleadosService } from 'src/app/shared/services/empleados.service';
import { CrearempleadoviewComponent } from '../crearempleadoview/crearempleadoview.component';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { VerempleadoviewComponent } from '../verempleadoview/verempleadoview.component';
import { FormControl, FormGroup } from '@angular/forms';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-empleadosview',
  templateUrl: './empleadosview.component.html',
  styles: [
  ]
})
export class EmpleadosviewComponent implements OnInit {
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  listaEmpleados:[]=[];
  displayedColumns=['id','Nombres','Apellidos','Edad','FechaNacimiento','Salario','Accion'];
  start:number=0;
  lenght:number=5;
  sizeTotal:number=0;
  pageEvent: PageEvent = new PageEvent();
  empleadoForm:FormGroup
  
  constructor(
    private _empleadoService:EmpleadosService,
    public dialog:MatDialog,
    public datePipe: DatePipe,
  ) { 
    this.pageEvent.pageIndex=0
    this.empleadoForm=new FormGroup({
      nombres:new FormControl(null),
      apellidos:new FormControl(null),
      edad:new FormControl(null),
      fecha_nacimiento:new FormControl(null),
      salario:new FormControl(null),
    });
  }

  ngOnInit(): void {
    this.getEmpleadoPaginado(this.start,this.lenght);
  }

  async getEmpleadoPaginado(pagina:number,cantidad:number){
    try {
      const response = await this._empleadoService.listarEmpleadosPaginado({pagina:pagina,cantidad:cantidad})
      this.pageEvent.pageIndex=0
      // console.table(response)
      this.listaEmpleados=response.content
      this.sizeTotal=response.totalElements
    } catch (error) {
      console.log(error)
    }

  }

  async paginacion(event:any){
    console.log("event",event)
    this.pageEvent=event;
    await this.getEmpleadoPaginado(event.pageIndex,this.lenght);
  }

  openCrearEmpleado(){
    const dialogRef = this.dialog.open(CrearempleadoviewComponent,{
      width:'500px',
      data:{title:'Registrar Empleado',result:false}
    });
    dialogRef.afterClosed().subscribe(result=> {
      console.log("Cerro modal",result);
      if (result.result) {
        this.paginator.firstPage();
        this.getEmpleadoPaginado(this.start,this.lenght);
      }

    })
  }

  async eliminarEmpleado(id:number){
    try {
      const response = await this._empleadoService.eliminarEmpleadoById({documento_id:id})
      if (response!=null) {
        this.pageEvent.pageIndex=0;
        this.paginator.firstPage();
        this.getEmpleadoPaginado(this.start,this.lenght);
      }else{
        //No se aelimino ningun registro
      }
      console.log(response)
    } catch (error) {
      console.log(error)
    }
  }
  visualizarEmpleado(id:number){
    const dialogRef = this.dialog.open(VerempleadoviewComponent,{
      data:{title:'Ver detalle Empleado',idEmpleado:id,isVista:true}
    })
    
  }

  actualizarEmpleado(id:number){
   const dialogRef = this.dialog.open(VerempleadoviewComponent,{
      data:{title:'Editar Empleado',idEmpleado:id,isVista:false}
    })
    dialogRef.afterClosed().subscribe(result=> {
      console.log("Cerro modal",result);
      if (result.result) {
        this.pageEvent.pageIndex=0;
        this.paginator.firstPage();
        this.getEmpleadoPaginado(this.start,this.lenght);
      }

    })
  }

  async filtrarPorCriterios(){
    try {
      var fecha="";
      if (this.empleadoForm.value.fecha_nacimiento!=null) {
        fecha = this.datePipe.transform(this.empleadoForm.value.fecha_nacimiento, 'yyyy-MM-dd')||""
      }
      const response = await this._empleadoService.listarEmpleadosPaginadoPorCriterio({
        pagina:this.start,
        cantidad:this.lenght,
        nombres:this.empleadoForm.value.nombres,
        apellidos:this.empleadoForm.value.apellidos,
        edad:this.empleadoForm.value.edad,
        fecha_nacimiento:fecha,
        salario:this.empleadoForm.value.salario
      })

      this.listaEmpleados=response.content
      this.sizeTotal=response.totalElements
    } catch (error) {
      console.log(error)
    }
  }


}
