package com.empleado.empleado.models.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.empleado.empleado.models.dao.IEmpleadoDao;
import com.empleado.empleado.models.entity.Empleado;
import com.empleado.empleado.models.entity.Paginacion;

import javax.transaction.Transactional;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService{
	
	@Autowired
	private IEmpleadoDao empleadoDao;

	@Override
	@Transactional
	public Empleado crear(Empleado empleado) {
		return empleadoDao.save(empleado);
	}

	@Override
	public List<Empleado> listarEmpleadosAll() {
		return empleadoDao.findAll();
	}

	@Override
	public Page<Empleado> listarEmpleadosPaginado(Paginacion paginado) {
		//Pageable page =PageRequest.of(paginado.getPagina(), paginado.getCantidad());
		return empleadoDao.findAll(PageRequest.of(paginado.getPagina(), paginado.getCantidad()));
	}
	
	@Override
	public Empleado actualizarEmpleado(Empleado empleado) {
		return empleadoDao.save(empleado);
	}

	@Override
	public Empleado findById(Long id) {
		return empleadoDao.findById(id).orElse(null);
	}

	@Override
	public String eliminarEmpleadoById(Long id) {
		try {
			empleadoDao.deleteById(id);
			return "0000";
		} catch (Exception e) {
			System.out.println("error::>"+e.getMessage());
			return "1005";
		}
	}

	@Override
	public Page<Empleado> listarEmpleadosPaginadoPorCriterios(Paginacion paginado, Optional<String> nombres, Optional<String> apellidos, Optional<Integer> edad, Optional<Date> fecha_nacimiento, Optional<Double> salario) {
		Pageable page =PageRequest.of(paginado.getPagina(), paginado.getCantidad());
		return empleadoDao.findByNombresAndApellidosAndEdadAndFecha_nacimientoAndSalario(nombres,apellidos,edad,fecha_nacimiento,salario,page);
	}


}
