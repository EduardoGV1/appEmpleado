import { DatePipe } from '@angular/common';
import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { EmpleadosService } from 'src/app/shared/services/empleados.service';

@Component({
  selector: 'app-verempleadoview',
  templateUrl: './verempleadoview.component.html',
  styles: [
  ]
})
export class VerempleadoviewComponent implements OnInit {
  isVista:boolean=false;
  isEdit:boolean=false;
  empleadoForm:FormGroup

  constructor(
    public dialogRef:MatDialogRef<VerempleadoviewComponent>,
    @Inject(MAT_DIALOG_DATA) public data:any,
    private _empleadoService:EmpleadosService,
    public datePipe: DatePipe
  ) {

    (data.isVista)?this.isVista=true:this.isEdit=true;
    this.empleadoForm=new FormGroup({
      nombres:new FormControl(null,[Validators.required]),
      apellidos:new FormControl(null,[Validators.required]),
      edad:new FormControl(null,[Validators.required]),
      fecha_nacimiento:new FormControl(null,[Validators.required]),
      salario:new FormControl(null,[Validators.required]),
    });
   }

  ngOnInit(): void {
    this.obtieneEmpleadoById();
    
  }

  async obtieneEmpleadoById(){
    try {
      const response = await this._empleadoService.listarEmpleadoById({documento_id:this.data.idEmpleado})
      let fecha = this.datePipe.transform(response.fecha_nacimiento,'yyyy-MM-dd')
      console.log(fecha)
      if (this.isVista) {
        
        this.empleadoForm.controls['nombres'].setValue(response.nombres)
        this.empleadoForm.controls['apellidos'].setValue(response.apellidos)
        this.empleadoForm.controls['edad'].setValue(response.edad)
        this.empleadoForm.controls['fecha_nacimiento'].setValue(fecha)
        this.empleadoForm.controls['salario'].setValue(response.salario)
        this.empleadoForm.disable();
      } else{
        this.empleadoForm.controls['nombres'].setValue(response.nombres)
        this.empleadoForm.controls['apellidos'].setValue(response.apellidos)
        this.empleadoForm.controls['edad'].setValue(response.edad)
        this.empleadoForm.controls['fecha_nacimiento'].setValue(fecha)
        this.empleadoForm.controls['salario'].setValue(response.salario)
      }
      
      console.log(response)
    } catch (error) {
      console.log(error)
    }
  }

  cancelar(){
    console.log("this.data")
    this.data.result=false
    this.dialogRef.close(this.data);
  }

  async actualizar(){
    this.data.result=true
      const response = await this._empleadoService.actualizarEmpleado(
        {
          nombres:this.empleadoForm.value.nombres,
          apellidos:this.empleadoForm.value.apellidos,
          edad:this.empleadoForm.value.edad,
          fecha_nacimiento:this.empleadoForm.value.fecha_nacimiento,
          salario:this.empleadoForm.value.salario
        },this.data.idEmpleado
      )
      console.log("response",response)
      this.dialogRef.close(this.data);
  }

  

}
