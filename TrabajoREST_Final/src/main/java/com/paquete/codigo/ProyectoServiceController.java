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

import com.paquete.codigo.modelo.Proyecto;

@RestController
public class ProyectoServiceController {
	private static Map<String, Proyecto> mapProyectos = new HashMap<>();

	static {
		Proyecto d1 = new Proyecto();
		d1.setId("1");
		d1.setNombre("Proyecto SOAP");
		mapProyectos.put(d1.getId(), d1);

		Proyecto d2 = new Proyecto();
		d2.setId("2");
		d2.setNombre("Proyecto REST");
		mapProyectos.put(d2.getId(), d2);
	}

	@DeleteMapping("/proyecto/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		mapProyectos.remove(id);
		return new ResponseEntity<>("Proyecto is deleted successsfully", HttpStatus.OK);
	}

	@PutMapping("/proyecto/{id}")
	public ResponseEntity<Object> updateDepartamento(@PathVariable("id") String id, @RequestBody Proyecto proy) {
		mapProyectos.remove(id);
		proy.setId(id);
		mapProyectos.put(id, proy);
		return new ResponseEntity<>("Proyecto is updated successsfully", HttpStatus.OK);
	}

	@PostMapping("/proyecto")
	public ResponseEntity<Object> createDepartamento(@RequestBody Proyecto proy) {
		mapProyectos.put(proy.getId(), proy);
		return new ResponseEntity<>("Proyecto is created successfully", HttpStatus.CREATED);
	}

	@GetMapping("/proyecto/{id}")
	public ResponseEntity<Object> getDepartamentoById(@PathVariable("id") String id) {
		return new ResponseEntity<>(mapProyectos.get(id), HttpStatus.OK);
	}

	@GetMapping("/proyecto")
	public ResponseEntity<Object> getDepartamento() {
		return new ResponseEntity<>(mapProyectos.values(), HttpStatus.OK);
	}
}

