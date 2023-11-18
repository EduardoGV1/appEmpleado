package com.empleado.empleado.models.dao;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empleado.empleado.models.entity.Empleado;

public interface IEmpleadoDao extends JpaRepository<Empleado, Long>{
	
	@Query("SELECT e FROM Empleado e WHERE (:nombres IS NULL OR e.nombres = :nombres) AND (:apellidos IS NULL OR e.apellidos = :apellidos) AND (:edad IS NULL OR e.edad = :edad) AND (:fecha_nacimiento IS NULL OR e.fecha_nacimiento = :fecha_nacimiento) AND (:salario IS NULL OR e.salario = :salario)")
	Page<Empleado> findByNombresAndApellidosAndEdadAndFecha_nacimientoAndSalario(
			@Param("nombres") Optional<String> nombres,
            @Param("apellidos") Optional<String> apellidos,
            @Param("edad") Optional<Integer> edad,
            @Param("fecha_nacimiento") Optional<Date> fecha_nacimiento,
            @Param("salario") Optional<Double> salario,
            Pageable page
			);
}
	