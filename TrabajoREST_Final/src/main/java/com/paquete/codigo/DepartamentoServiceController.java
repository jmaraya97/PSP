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

import com.paquete.codigo.modelo.Departamento;
import com.paquete.codigo.modelo.Empleado;

@RestController
public class DepartamentoServiceController {
	private static Map<String, Departamento> mapDepartamentos = new HashMap<>();

	static {
		Departamento d1 = new Departamento();
		d1.setId("1");
		d1.setNombreDep("Comercial");
		mapDepartamentos.put(d1.getId(), d1);

		Departamento d2 = new Departamento();
		d2.setId("2");
		d2.setNombreDep("Desarrollo");
		mapDepartamentos.put(d2.getId(), d2);
	}

	private static Map<String, Empleado> mapEmpleados = new HashMap<>();
	static {
		Empleado e1 = new Empleado();
		e1.setId("1");
		e1.setNombre("Juan");
		e1.setEdad(23);
		e1.setDepartamento(mapDepartamentos.get("1"));
		mapEmpleados.put(e1.getId(), e1);

		Empleado e2 = new Empleado();
		e2.setId("2");
		e2.setNombre("Paco");
		e2.setEdad(53);
		e2.setDepartamento(mapDepartamentos.get("2"));
		mapEmpleados.put(e2.getId(), e2);

		Empleado e3 = new Empleado();
		e3.setId("3");
		e3.setNombre("Carlos");
		e3.setEdad(26);
		e3.setDepartamento(mapDepartamentos.get("2"));
		mapEmpleados.put(e3.getId(), e3);
	}

	public static Map<String, Departamento> getMapDepartamentos() {
		return mapDepartamentos;
	}

	@DeleteMapping("/dep/{idDep}")
	public ResponseEntity<Object> delete(@PathVariable("idDep") String id) {
		mapDepartamentos.remove(id);
		return new ResponseEntity<>("Departamento is deleted successsfully", HttpStatus.OK);
	}

	@PutMapping("/dep/{idDep}")
	public ResponseEntity<Object> updateDepartamento(@PathVariable("idDep") String id, @RequestBody Departamento dep) {
		mapDepartamentos.remove(id);
		dep.setId(id);
		mapDepartamentos.put(id, dep);
		return new ResponseEntity<>("Departamento is updated successsfully", HttpStatus.OK);
	}

	@PostMapping("/dep")
	public ResponseEntity<Object> createDepartamento(@RequestBody Departamento dep) {
		mapDepartamentos.put(dep.getId(), dep);
		return new ResponseEntity<>("Departamento is created successfully", HttpStatus.CREATED);
	}

	@GetMapping("/dep/{idDep}")
	public ResponseEntity<Object> getDepartamentoById(@PathVariable("idDep") String id) {
		return new ResponseEntity<>(mapDepartamentos.get(id), HttpStatus.OK);
	}

	@GetMapping("/dep")
	public ResponseEntity<Object> getDepartamento() {
		return new ResponseEntity<>(mapDepartamentos.values(), HttpStatus.OK);
	}

	//----------------------------------------------------------------------------------------------------------------------------------//
	//------------------------------------------------------Consultas Empleados---------------------------------------------------------//
	//----------------------------------------------------------------------------------------------------------------------------------//
	@DeleteMapping("/dep/{idDep}/empleados/{id}")
	public ResponseEntity<Object> deleteEmpleado(@PathVariable("idDep") String idDep, @PathVariable("id") String id) {
		if (mapEmpleados.get(id).getDepartamento().getId().equals(idDep)) {
			mapEmpleados.remove(id);
			return new ResponseEntity<>("Empleado is deleted successsfully", HttpStatus.OK);
		} else
			return new ResponseEntity<>("Empleado no se ha borrado porque no pertenece al departamento indicado",
					HttpStatus.OK);

	}

	@PutMapping("/dep/{idDep}/empleados/{id}")
	public ResponseEntity<Object> updateEmpleado(@PathVariable("idDep") String idDep, @PathVariable("id") String id,
			@RequestBody Empleado empleado) {
		if (mapEmpleados.get(id).getDepartamento().getId().equals(idDep)) {
			mapEmpleados.remove(id);
			empleado.setId(id);
			mapEmpleados.put(id, empleado);
			return new ResponseEntity<>("Empleado is updated successsfully", HttpStatus.OK);
		} else
			return new ResponseEntity<>("Empleado no se ha actualizado porque no pertenece al departamento indicado",
					HttpStatus.OK);
	}

	@PostMapping("/dep/{idDep}/empleados")
	public ResponseEntity<Object> createEmpleado(@PathVariable("idDep") String idDep, @RequestBody Empleado empleado) {
		empleado.setDepartamento(mapDepartamentos.get(idDep));
		mapEmpleados.put(empleado.getId(), empleado);
		return new ResponseEntity<>("Empleado is created successfully", HttpStatus.CREATED);

	}

	@GetMapping("/dep/{idDep}/empleados/{id}")
	public ResponseEntity<Object> getEmpleadoById(@PathVariable("idDep") String idDep, @PathVariable("id") String id) {
		if (mapEmpleados.get(id).getDepartamento().getId().equals(idDep)) {
			return new ResponseEntity<>(mapEmpleados.get(id), HttpStatus.OK);
		} else
			return new ResponseEntity<>("Empleado no puede ser mostrado porque no pertenece a este departamento",
					HttpStatus.CREATED);
	}

	@GetMapping("/dep/{idDep}/empleados")
	public ResponseEntity<Object> getEmpleados(@PathVariable("idDep") String idDep) {
		Map<String, Empleado> empleadosDepartamento = new HashMap<>();
		for (int id = 1; id <= mapEmpleados.size(); id++) {
			if (mapEmpleados.get(String.valueOf(id)).getDepartamento().getId().equals(idDep)) {
				empleadosDepartamento.put(String.valueOf(id),mapEmpleados.get(String.valueOf(id)));
			}
		}
		return new ResponseEntity<>(empleadosDepartamento.values(), HttpStatus.OK);
	}
}
