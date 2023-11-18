package com.empleado.empleado.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.empleado.empleado.models.entity.Empleado;
import com.empleado.empleado.models.entity.Paginacion;
import com.empleado.empleado.models.service.IEmpleadoService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;



@RestController
@RequestMapping("Empleado")

public class EmpleadoController {
	
	@Autowired
	private IEmpleadoService empleadoService;

	///METODO PARA CREAR UN EMPLEADO
	@PostMapping(path = "createEmpleado")
	@ResponseStatus(HttpStatus.CREATED)
	public Empleado crear(@RequestBody Empleado empleado) {
		System.out.println("Request entrante::"+empleado.getFecha_nacimiento());
		return empleadoService.crear(empleado);
	}
	
	//MEOTODO PARA LISTAR EMPLEADOS ALL
	@GetMapping(path = "listarEmpleadosAll")
	@ResponseStatus(HttpStatus.OK)
	public List<Empleado> listarEmpleadosAll(){
		return empleadoService.listarEmpleadosAll();
	}
	
	@GetMapping(path = "listarEmpleadosPaginado")
	@ResponseStatus(HttpStatus.OK)
	public Page<Empleado> listarEmpleadosPaginado(@RequestParam Integer pagina,@RequestParam Integer cantidad){
		Paginacion paginacion = new Paginacion();
		paginacion.setPagina(pagina);
		paginacion.setCantidad(cantidad);
		return empleadoService.listarEmpleadosPaginado(paginacion);
	}
	
	@GetMapping(path = "listarEmpleadosPaginadoPorCriterio")
	@ResponseStatus(HttpStatus.OK)
	public Page<Empleado> listarEmpleadosPaginadoPorCriterio(@RequestParam Integer pagina,@RequestParam Integer cantidad
			,@RequestParam Optional<String> nombres ,@RequestParam Optional<String> apellidos,@RequestParam Optional<Integer> edad,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<Date> fecha_nacimiento,@RequestParam Optional<Double> salario){
		Paginacion paginacion = new Paginacion();
		paginacion.setPagina(pagina);
		paginacion.setCantidad(cantidad);
		Empleado empleado = new Empleado();
		/*
		 * if (fecha_nacimiento !=null && !fecha_nacimiento.isEmpty()) {
		 * SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd"); Date
		 * fecha = formatoFecha.parse(fecha_nacimiento); }
		 */
		return empleadoService.listarEmpleadosPaginadoPorCriterios(paginacion,nombres,apellidos,edad,fecha_nacimiento,salario);
	}
	
	@GetMapping(path = "listarEmpleadoById")
	@ResponseStatus(HttpStatus.OK)
	public Empleado listarEmpleadoById(@RequestParam Integer documento_id) {
		Long id = documento_id.longValue();
		Empleado empleadoEncontrado = empleadoService.findById(id);
		return empleadoEncontrado;
	}
	
	@Transactional
	@PutMapping(path = "editar")
	public Empleado actualizarEmpleado(@RequestBody Empleado empleado,@RequestParam Long documento_id) {
		Empleado empleadoEncontrado = empleadoService.findById(documento_id);
		if (empleadoEncontrado != null) {
			empleadoEncontrado.setNombres(empleado.getNombres());
			empleadoEncontrado.setApellidos(empleado.getApellidos());
			empleadoEncontrado.setEdad(empleado.getEdad());
			empleadoEncontrado.setFecha_nacimiento(empleado.getFecha_nacimiento());
			empleadoEncontrado.setSalario(empleado.getSalario());
			return empleadoEncontrado;
		} else {
			new EntityNotFoundException("Usuario no encontrado con ID: " + documento_id);
		}
		return empleadoEncontrado;
		
		//empleado.setNombres(empleadoEncontrado.getNombres());
		//return empleadoService.actualizarEmpleado(empleado);
		
	}
	
	@DeleteMapping(path = "eliminarById")
	@ResponseStatus(HttpStatus.OK)
	//@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Empleado> elminarEmpleadoById(@RequestParam Integer documento_id) {
		Long id = documento_id.longValue();
		Empleado empleadoEncontrado = empleadoService.findById(id);
		if (empleadoEncontrado!=null) {
			empleadoService.eliminarEmpleadoById(id);
		}
		return ResponseEntity.ok(empleadoEncontrado);
	}
}
