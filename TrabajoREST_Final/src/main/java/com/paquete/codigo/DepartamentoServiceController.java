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

	public static Map<String, Departamento> getMapDepartamentos() {
		return mapDepartamentos;
	}

	@DeleteMapping("/empleados/dep/{idDep}")
	public ResponseEntity<Object> delete(@PathVariable("idDep") String id) {
		mapDepartamentos.remove(id);
		return new ResponseEntity<>("Departamento is deleted successsfully", HttpStatus.OK);
	}

	@PutMapping("/empleados/dep/{idDep}")
	public ResponseEntity<Object> updateDepartamento(@PathVariable("idDep") String id, @RequestBody Departamento dep) {
		mapDepartamentos.remove(id);
		dep.setId(id);
		mapDepartamentos.put(id, dep);
		return new ResponseEntity<>("Departamento is updated successsfully", HttpStatus.OK);
	}

	@PostMapping("/empleados/dep")
	public ResponseEntity<Object> createDepartamento(@RequestBody Departamento dep) {
		mapDepartamentos.put(dep.getId(), dep);
		return new ResponseEntity<>("Departamento is created successfully", HttpStatus.CREATED);
	}

	@GetMapping("/empleados/dep/{idDep}")
	public ResponseEntity<Object> getDepartamentoById(@PathVariable("idDep") String id) {
		return new ResponseEntity<>(mapDepartamentos.get(id), HttpStatus.OK);
	}

	@GetMapping("/empleados/dep")
	public ResponseEntity<Object> getDepartamento() {
		return new ResponseEntity<>(mapDepartamentos.values(), HttpStatus.OK);
	}
}
