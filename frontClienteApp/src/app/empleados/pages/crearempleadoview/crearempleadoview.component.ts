import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { EmpleadosService } from 'src/app/shared/services/empleados.service';

@Component({
  selector: 'app-crearempleadoview',
  templateUrl: './crearempleadoview.component.html',
  styles: [
  ]
})
export class CrearempleadoviewComponent implements OnInit {
  empleadoForm:FormGroup
  constructor(
    public dialogRef:MatDialogRef<CrearempleadoviewComponent>,
    @Inject(MAT_DIALOG_DATA) public data:any,
    private _empleadoService:EmpleadosService
  ) { 
    this.empleadoForm=new FormGroup({
      nombres:new FormControl(null,[Validators.required]),
      apellidos:new FormControl(null,[Validators.required]),
      edad:new FormControl(null,[Validators.required]),
      fecha_nacimiento:new FormControl(null,[Validators.required]),
      salario:new FormControl(null,[Validators.required]),
    });
  }

  ngOnInit(): void {
    console.log(this.data)
    // this.data.result=true
  }

  async registrar(){
    console.log("this.data")
    if(this.empleadoForm.valid){
      this.data.result=true
      const response = await this._empleadoService.createEmpleado(
        {
          nombres:this.empleadoForm.value.nombres,
          apellidos:this.empleadoForm.value.apellidos,
          edad:this.empleadoForm.value.edad,
          fecha_nacimiento:this.empleadoForm.value.fecha_nacimiento,
          salario:this.empleadoForm.value.salario
        }
      )
      console.log("response",response)
      this.dialogRef.close(this.data);
    }else{
      return this.markFormGroupTouched(this.empleadoForm)
    }
  }
  markFormGroupTouched(formGroup: FormGroup) {
    Object.values(formGroup.controls).forEach(control => {
      control.markAsTouched();
  
      if (control instanceof FormGroup) {
        this.markFormGroupTouched(control);
      }
    });
}
  cancelar(){
    console.log("this.data")
    this.data.result=false
    this.dialogRef.close(this.data);
  }

}
