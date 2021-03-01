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
	// Map que hace la funcion de base de datos de la tabla Proyectos
	private static Map<String, Proyecto> mapProyectos = new HashMap<>();

	// Creacion de datos y agregacion de estos al map
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

	/**
	 * Metodo que elimina el proyecto indicado en la URL mediante su ID
	 * 
	 * @param id recibido desde la URL
	 * @return Respuesta que indica el resultado
	 */
	@DeleteMapping("/proyecto/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		if (mapProyectos.get(id) != null) {
			mapProyectos.remove(id);
			return new ResponseEntity<>("Proyecto is deleted successsfully", HttpStatus.OK);
		} else
			return new ResponseEntity<>("Proyecto no existe", HttpStatus.OK);
	}

	/**
	 * Metodo que actualiza el proyecto indicado en la URL mediante su ID
	 * 
	 * @param id recibido desde la URL
	 * @return Respuesta que indica el resultado
	 */
	@PutMapping("/proyecto/{id}")
	public ResponseEntity<Object> updateDepartamento(@PathVariable("id") String id, @RequestBody Proyecto proy) {
		if (mapProyectos.get(id) != null) {
			mapProyectos.remove(id);
			proy.setId(id);
			mapProyectos.put(id, proy);
			return new ResponseEntity<>("Proyecto is updated successsfully", HttpStatus.OK);
		} else
			return new ResponseEntity<>("Proyecto no existe", HttpStatus.OK);
	}

	/**
	 * Metodo que crea el proyecto indicado
	 * 
	 * @return Respuesta que indica el resultado
	 */
	@PostMapping("/proyecto")
	public ResponseEntity<Object> createDepartamento(@RequestBody Proyecto proy) {
		if (proy.getId() != null && mapProyectos.get(proy.getId())==null) {
			mapProyectos.put(proy.getId(), proy);
			return new ResponseEntity<>("Proyecto is created successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Proyecto no se creó", HttpStatus.CREATED);
		}
	}

	/**
	 * Metodo que muestra el proyecto indicado en la URL mediante su ID
	 * 
	 * @param id recibido desde la URL
	 * @return Respuesta que indica el resultado
	 */
	@GetMapping("/proyecto/{id}")
	public ResponseEntity<Object> getDepartamentoById(@PathVariable("id") String id) {
		if (mapProyectos.get(id) != null) {
			return new ResponseEntity<>(mapProyectos.get(id), HttpStatus.OK);
		} else
			return new ResponseEntity<>("Proyecto no existe", HttpStatus.OK);
	}

	/**
	 * Metodo que muestra los proyectos
	 * 
	 * @return Respuesta que indica el resultado
	 */
	@GetMapping("/proyecto")
	public ResponseEntity<Object> getDepartamento() {
		return new ResponseEntity<>(mapProyectos.values(), HttpStatus.OK);
	}
}
