package com.paquete.codigo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paquete.codigo.modelo.Empleado;

@RestController
public class EmpleadoServiceController {
	private static Map<String, Empleado> mapEmpleados = new HashMap<>();
	static {
		Empleado e1 = new Empleado();
		e1.setId("1");
		e1.setNombre("Juan");
		e1.setEdad(23);
		e1.setDepartamento(DepartamentoServiceController.getMapDepartamentos().get("Comercial"));
		mapEmpleados.put(e1.getId(), e1);

		Empleado e2 = new Empleado();
		e2.setId("2");
		e2.setNombre("Paco");
		e2.setEdad(53);
		e1.setDepartamento(DepartamentoServiceController.getMapDepartamentos().get("Desarollo"));
		mapEmpleados.put(e2.getId(), e2);

		Empleado e3 = new Empleado();
		e3.setId("3");
		e3.setNombre("Carlos");
		e3.setEdad(26);
		e1.setDepartamento(DepartamentoServiceController.getMapDepartamentos().get("Desarrollo"));
		mapEmpleados.put(e3.getId(), e3);
	}

	@DeleteMapping("/empleados/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		mapEmpleados.remove(id);
		return new ResponseEntity<>("Empleado is deleted successsfully", HttpStatus.OK);
	}

	@PutMapping("/empleados/{id}")
	public ResponseEntity<Object> updateEmpleado(@PathVariable("id") String id, @RequestBody Empleado empleado) {
		mapEmpleados.remove(id);
		empleado.setId(id);
		mapEmpleados.put(id, empleado);
		return new ResponseEntity<>("Empleado is updated successsfully", HttpStatus.OK);
	}

	@PostMapping("/empleados/{id}")
	public ResponseEntity<Object> createEmpleado(@RequestBody Empleado empleado) {
		mapEmpleados.put(empleado.getId(), empleado);
		return new ResponseEntity<>("Empleado is created successfully", HttpStatus.CREATED);
	}

	@GetMapping("/empleados/{id}")
	public ResponseEntity<Object> getEmpleadoById(@PathVariable("id") String id) {
		return new ResponseEntity<>(mapEmpleados.get(id), HttpStatus.OK);
	}

	@GetMapping("/empleados")
	public ResponseEntity<Object> getEmpleados() {
		return new ResponseEntity<>(mapEmpleados.values(), HttpStatus.OK);
	}
}
