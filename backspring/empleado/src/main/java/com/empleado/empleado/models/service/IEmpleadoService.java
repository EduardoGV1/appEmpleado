package com.empleado.empleado.models.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.empleado.empleado.models.entity.Empleado;
import com.empleado.empleado.models.entity.Paginacion;

public interface IEmpleadoService {
	public Empleado crear(Empleado empleado);
	public List<Empleado> listarEmpleadosAll();
	public Page<Empleado> listarEmpleadosPaginado(Paginacion paginado);
	public Page<Empleado> listarEmpleadosPaginadoPorCriterios(Paginacion paginado,Optional<String> nombres, Optional<String> apellidos,Optional<Integer> edad, Optional<Date> fecha_nacimiento, Optional<Double> salario);
	public Empleado actualizarEmpleado(Empleado empleado);
	public Empleado findById(Long id);
	public String eliminarEmpleadoById(Long id);
}
