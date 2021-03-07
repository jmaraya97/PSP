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

/**
 * Clase que controla el uso y peticiones de Departamento y empleado
 * @author usuario
 *
 */
@RestController
public class DepartamentoServiceController {
	// Map que hace la funcion de base de datos de la tabla Departamentos
	private static Map<String, Departamento> mapDepartamentos = new HashMap<>();
	// Map que hace la funcion de base de datos de la tabla Empleado
	private static Map<String, Empleado> mapEmpleados = new HashMap<>();

	// Creacion de datos y agregacion de estos al map
		static {
			Empleado e1 = new Empleado();
			e1.setId("1");
			e1.setNombre("Juan");
			e1.setEdad(23);
			mapEmpleados.put(e1.getId(), e1);

			Empleado e2 = new Empleado();
			e2.setId("2");
			e2.setNombre("Paco");
			e2.setEdad(53);
			mapEmpleados.put(e2.getId(), e2);

			Empleado e3 = new Empleado();
			e3.setId("3");
			e3.setNombre("Carlos");
			e3.setEdad(26);
			mapEmpleados.put(e3.getId(), e3);
		}
		
		
	// Creacion de datos y agregacion de estos al map
	static {
		Departamento d1 = new Departamento();
		d1.setId("1");
		d1.setNombreDep("Comercial");
		d1.getListaEmpleados().put(mapEmpleados.get("1").getId(),mapEmpleados.get("1"));
		d1.getListaEmpleados().put(mapEmpleados.get("2").getId(),mapEmpleados.get("2"));
		mapDepartamentos.put(d1.getId(), d1);

		Departamento d2 = new Departamento();
		d2.setId("2");
		d2.setNombreDep("Desarrollo");
		d2.getListaEmpleados().put(mapEmpleados.get("3").getId(),mapEmpleados.get("3"));
		mapDepartamentos.put(d2.getId(), d2);
	}

	

	/**
	 * Metodo que elimina el departamento indicado en la URL mediante su ID
	 * 
	 * @param idDep recibido desde la URL
	 * @return Respuesta que indica que todo ha ido correcto
	 */
	@DeleteMapping("/dep/{idDep}")
	public ResponseEntity<Object> delete(@PathVariable("idDep") String id) {
		if (mapDepartamentos.get(id) != null) {
			mapDepartamentos.remove(id);
			return new ResponseEntity<>("Departamento eliminado correctamente", HttpStatus.OK);
		} else
			return new ResponseEntity<>("Departamento no existe", HttpStatus.OK);
	}

	/**
	 * Metodo que actualiza el departamento indicado en la URL mediante su ID
	 * 
	 * @param idDep recibido desde la URL
	 * @return Respuesta que indica que todo ha ido correcto
	 */
	@PutMapping("/dep/{idDep}")
	public ResponseEntity<Object> updateDepartamento(@PathVariable("idDep") String id, @RequestBody Departamento dep) {
		if (mapDepartamentos.get(id) != null) {
			HashMap<String,Empleado>empleados=mapDepartamentos.get(id).getListaEmpleados();
			mapDepartamentos.remove(id);
			dep.setId(id);
			dep.setListaEmpleados(empleados);
			mapDepartamentos.put(id, dep);
			return new ResponseEntity<>("Departamento actualizado correctamente", HttpStatus.OK);
		} else
			return new ResponseEntity<>("Departamento no existe", HttpStatus.OK);
	}

	/**
	 * Metodo que crea el departamento pasado mediante JSON
	 * 
	 * @param dep
	 * @return Respuesta que indica que todo ha ido correcto
	 */
	@PostMapping("/dep")
	public ResponseEntity<Object> createDepartamento(@RequestBody Departamento dep) {
		if (dep.getId() != null && mapDepartamentos.get(dep.getId()) == null) {
			mapDepartamentos.put(dep.getId(), dep);
			return new ResponseEntity<>("Departamento creado correctamente", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Departamento no se creó porque ya existe o la informacion no es válida", HttpStatus.CREATED);
		}
	}

	/**
	 * Metodo muestra el departamento indicado en la URL mediante su ID
	 * 
	 * @param idDep recibido desde la URL
	 * @return Respuesta que indica que todo ha ido correcto
	 */
	@GetMapping("/dep/{idDep}")
	public ResponseEntity<Object> getDepartamentoById(@PathVariable("idDep") String id) {
		if (mapDepartamentos.get(id) != null) {
			return new ResponseEntity<>(mapDepartamentos.get(id), HttpStatus.OK);
		} else
			return new ResponseEntity<>("Departamento no existe", HttpStatus.OK);
	}

	/**
	 * Metodo muestra todos los departamentos
	 * 
	 * @return Respuesta que muestra los departamentos en formato JSON
	 */
	@GetMapping("/dep")
	public ResponseEntity<Object> getDepartamento() {
		if (mapDepartamentos.isEmpty()) {
			return new ResponseEntity<>("No hay departamentos", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(mapDepartamentos.values(), HttpStatus.OK);
		}
	}

	// ----------------------------------------------------------------------------------------------------------------------------------//
	// ------------------------------------------------------Consultas
	// Empleados---------------------------------------------------------//
	// ----------------------------------------------------------------------------------------------------------------------------------//
	/**
	 * Elimina a un Empleado indicado en la URL, SOLO en caso de que el empleado
	 * pertenezca al departamento indicado
	 * 
	 * @param idDep recibido desde la URL y hace refencia al id del Departamento
	 * @param id    recibido desde la URL y hace refencia al id del Empleado
	 * @return respuesta que indica si se ha podido o no realizar lo que se pide
	 */
	@DeleteMapping("/dep/{idDep}/empleados/{id}")
	public ResponseEntity<Object> deleteEmpleado(@PathVariable("idDep") String idDep, @PathVariable("id") String id) {
		if (mapEmpleados.get(id) != null && mapDepartamentos.get(idDep).getListaEmpleados().containsKey(id)) {
			mapEmpleados.remove(id);
			mapDepartamentos.get(idDep).borraEmpleado(id);
			return new ResponseEntity<>("Empleado eliminado correctamente", HttpStatus.OK);
		} else
			return new ResponseEntity<>("Empleado no se ha borrado porque no pertenece al departamento indicado o no existe",
					HttpStatus.OK);

	}

	/**
	 * Actualiza a un Empleado indicado en la URL, SOLO en caso de que el empleado
	 * pertenezca al departamento indicado
	 * 
	 * @param idDep recibido desde la URL y hace refencia al id del Departamento
	 * @param id    recibido desde la URL y hace refencia al id del Empleado
	 * @return respuesta que indica si se ha podido o no realizar lo que se pide
	 */
	@PutMapping("/dep/{idDep}/empleados/{id}")
	public ResponseEntity<Object> updateEmpleado(@PathVariable("idDep") String idDep, @PathVariable("id") String id,
			@RequestBody Empleado empleado) {
		if (mapEmpleados.get(id) != null && mapDepartamentos.get(idDep).getListaEmpleados().containsKey(id) && mapEmpleados.containsKey(id)) {
			mapEmpleados.remove(id);
			mapDepartamentos.get(idDep).getListaEmpleados().remove(id);
			empleado.setId(id);
			mapEmpleados.put(id, empleado);
			mapDepartamentos.get(idDep).getListaEmpleados().put(id, empleado);
			return new ResponseEntity<>("Empleado actualizado correctamente", HttpStatus.OK);
		} else
			return new ResponseEntity<>("Empleado no se ha actualizado porque no pertenece al departamento indicado o no existe",
					HttpStatus.OK);
	}

	/**
	 * Crea un Empleado con los datos recibidos en el Departamento indicado en la
	 * URL
	 * 
	 * @param idDep    recibido desde la URL y hace refencia al id del Departamento
	 * @param Empleado
	 * @return respuesta que indica si se ha podido o no realizar lo que se pide
	 */
	@PostMapping("/dep/{idDep}/empleados")
	public ResponseEntity<Object> createEmpleado(@PathVariable("idDep") String idDep, @RequestBody Empleado empleado) {
		if (empleado.getId() != null && mapEmpleados.get(empleado.getId()) == null && mapDepartamentos.containsKey(idDep)) {
			mapEmpleados.put(empleado.getId(), empleado);
			mapDepartamentos.get(idDep).agregaEmpleado(empleado);
			return new ResponseEntity<>("Empleado se creo correctamente", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Empleado no se creó porque el departamento no existe", HttpStatus.CREATED);
		}

	}

	/**
	 * Muestra el empleado del departamento indicado
	 * 
	 * @param idDep recibido desde la URL y hace refencia al id del Departamento
	 * @param id    recibido desde la URL y hace refencia al id del Empleado
	 * @return respuesta que indica si se ha podido o no realizar lo que se pide
	 */
	@GetMapping("/dep/{idDep}/empleados/{id}")
	public ResponseEntity<Object> getEmpleadoById(@PathVariable("idDep") String idDep, @PathVariable("id") String id) {
		if (mapEmpleados.get(id) != null && mapDepartamentos.get(idDep).getListaEmpleados().containsKey(id)) {
			return new ResponseEntity<>(mapEmpleados.get(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Empleado no pertenece al departamento o no existe",
					HttpStatus.CREATED);
		}
	}

	/**
	 * Muestra los empleados del departamento indicado
	 * 
	 * @param idDep recibido desde la URL y hace refencia al id del Departamento
	 * @param id    recibido desde la URL y hace refencia al id del Empleado
	 * @return respuesta que indica si se ha podido o no realizar lo que se pide
	 */
	@GetMapping("/dep/{idDep}/empleados")
	public ResponseEntity<Object> getEmpleados(@PathVariable("idDep") String idDep) {
		if (!mapDepartamentos.containsKey(idDep) || mapDepartamentos.get(idDep).getListaEmpleados().isEmpty()) {
			return new ResponseEntity<>("No hay empleados en este departamento", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(mapDepartamentos.get(idDep).getListaEmpleados().values(), HttpStatus.OK);
		}
	}
	
}
