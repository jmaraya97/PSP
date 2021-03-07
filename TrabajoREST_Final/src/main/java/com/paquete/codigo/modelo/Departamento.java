package com.paquete.codigo.modelo;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase POJO
 * @author Juan Martin
 * 
 */
public class Departamento {
	String id;
	String nombreDep;
	int capacidadTrabajadores;
	HashMap<String,Empleado> listaEmpleados;

	public Departamento() {
		this.listaEmpleados=new HashMap<>();
	}
	/**
	 * 
	 * @return String id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Establece el id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	public String getNombreDep() {
		return nombreDep;
	}
	public void setNombreDep(String nombreDep) {
		this.nombreDep = nombreDep;
	}
	public int getCapacidadTrabajadores() {
		return capacidadTrabajadores;
	}
	public void setCapacidadTrabajadores(int capacidadTrabajadores) {
		this.capacidadTrabajadores = capacidadTrabajadores;
	}

	public HashMap<String, Empleado> getListaEmpleados() {
		return listaEmpleados;
	}

	public void setListaEmpleados(HashMap<String, Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}
	
	public void agregaEmpleado(Empleado empleado) {
		this.listaEmpleados.put(empleado.getId(), empleado);
	}
	
	public void borraEmpleado(String id) {
		this.listaEmpleados.remove(id);
	}
	
	
	
}
