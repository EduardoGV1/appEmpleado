package com.empleado.empleado;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.empleado.empleado.models.entity.Empleado;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmpleadoApplicationTests {
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void testEndPoint() {
		ResponseEntity<List<Empleado>> response = restTemplate.exchange("/Empleado/listarEmpleadosAll", HttpMethod.GET,null,new ParameterizedTypeReference<List<Empleado>>() {
		});
		
		assertEquals(200, response.getStatusCodeValue());
		List<Empleado> empleados = response.getBody();
		assertNotNull(empleados);
	}
	
	@Test
	public void testEndPointcreateEmpleado() {
		Empleado empleado = new Empleado();
		empleado.setNombres("Nombre-1");
		empleado.setApellidos("Apellido 1");
		empleado.setEdad(20);
		empleado.setFecha_nacimiento(new Date());
		empleado.setSalario(2000.10);
		
		HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        HttpEntity<Empleado> requestEntity = new HttpEntity<>(empleado, headers);
		ResponseEntity<Empleado> response = restTemplate.exchange(
                "/Empleado/createEmpleado",
                HttpMethod.POST,
                requestEntity,
                Empleado.class);
        // Verificar la respuesta
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Empleado empleadoCreado = response.getBody();
        assertNotNull(empleadoCreado);
        assertNotNull(empleadoCreado.getDocumento_id()); 
	}
	
	@Test
	public void testEndPointlistarEmpleadosPaginado() {
		HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        Page page = null;

        HttpEntity<Page<Empleado>> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<Page> response = (ResponseEntity<Page>) restTemplate.exchange(
                "/Empleado/listarEmpleadosPaginado?cantidad=5&pagina=1",
                HttpMethod.GET,
                requestEntity,
                (page).getClass());
		assertEquals(200, response.getStatusCodeValue());
		List<Empleado> empleados = response.getBody().getContent();
		assertNotNull(empleados);
	}
	
	@Test
	public void testEndPointlistarEmpleadoById() {
		
		HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
		HttpEntity<Empleado> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<Empleado> response = restTemplate.exchange(
                "/Empleado/listarEmpleadoById?documento_id=51",
                HttpMethod.GET,
                requestEntity,
                Empleado.class);
		
		assertEquals(200, response.getStatusCodeValue());
		Empleado empleado = response.getBody();
		assertNotNull(empleado);
	}
	
	@Test
	public void testEndPointeditarEmpleado() {
		Empleado empleado = new Empleado();
		empleado.setNombres("Nombre-1");
		empleado.setApellidos("Apellido 1");
		empleado.setEdad(20);
		empleado.setFecha_nacimiento(new Date());
		empleado.setSalario(2000.10);
		
		HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        // Crear una entidad HTTP con el cuerpo y los encabezados
        HttpEntity<Empleado> requestEntity = new HttpEntity<>(empleado, headers);
		ResponseEntity<Empleado> response = restTemplate.exchange(
                "/Empleado/editar?documento_id=4",
                HttpMethod.PUT,
                requestEntity,
                Empleado.class);
        // Verificar la respuesta
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Empleado empleadoCreado = response.getBody();
        assertNotNull(empleadoCreado);
        assertNotNull(empleadoCreado.getDocumento_id()); 
	}
	
	@Test
	public void testEndPointeliminarById() {
		
		
		HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        HttpEntity<Empleado> requestEntity = new HttpEntity<>(headers);
        //cambiar por un id existente
		ResponseEntity<Empleado> response = restTemplate.exchange(
                "/Empleado/eliminarById?documento_id=53",
                HttpMethod.DELETE,
                requestEntity,
                Empleado.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Empleado empleadoEliminado = response.getBody();
        assertNotNull(empleadoEliminado);
        assertNotNull(empleadoEliminado.getDocumento_id()); 
	}

}
