import { Injectable } from "@angular/core";
import { BaseService } from "./base.service";
import { HttpClient, HttpParams } from "@angular/common/http";
import { EMPLEADO_URL } from "../utils/url_constants";

@Injectable({
    providedIn:'root'
})
export class EmpleadosService extends BaseService{
    constructor(
        private http:HttpClient
    ){
        super()
    }


    listarEmpleadosPaginado(data:{
        pagina:number;
        cantidad:number;
    }):Promise<any>{
        return this.http.get(`${EMPLEADO_URL}listarEmpleadosPaginado`,{params:data}).toPromise();
    }

    listarEmpleadoById(data:{
        documento_id:number;
    }):Promise<any>{
        return this.http.get(`${EMPLEADO_URL}listarEmpleadoById`,{params:data}).toPromise();
    }
    listarEmpleadosPaginadoPorCriterio(data:{
        pagina:number;
        cantidad:number;
        nombres?:string;
        apellidos?:string;
        edad?:number;
        fecha_nacimiento?:string;
        salario?:number;
    }):Promise<any>{
        var params =new HttpParams()
        .set('pagina',data.pagina)
        .set('cantidad',data.cantidad);
        if (data.nombres!= null) {
            params =  params.set('nombres', data.nombres);
        }
        
        if (data.apellidos != null) {
            params = params.set('apellidos', data.apellidos);
        }
        
        if (data.edad!= null) {
            params = params.set('edad', data.edad);
        }
        
        if (data.fecha_nacimiento!= null || data.fecha_nacimiento!="") {
            params = params.set('fecha_nacimiento', data.fecha_nacimiento||"");
        }
        
        if (data.salario!= null) {
            params = params.set('salario', data.salario);
        }
        return this.http.get(`${EMPLEADO_URL}listarEmpleadosPaginadoPorCriterio`,{params}).toPromise();
    }

    createEmpleado(data:{
        nombres:string;
        apellidos:string;
        edad:number;
        fecha_nacimiento:string;
        salario:number;
    }):Promise<any>{
        return this.http.post(`${EMPLEADO_URL}createEmpleado`,data).toPromise();
    }

    actualizarEmpleado(data:{
        nombres:string;
        apellidos:string;
        edad:number;
        fecha_nacimiento:string;
        salario:number;
    }, id:number):Promise<any>{
        return this.http.put(`${EMPLEADO_URL}editar?documento_id=`+id,data).toPromise();
    }

    eliminarEmpleadoById(data:{
        documento_id:number;
    }):Promise<any>{
        return this.http.delete(`${EMPLEADO_URL}eliminarById`,{params:data}).toPromise();
    }
}