package com.paquete.codigo.modelo;

/**
 * Clase POJO
 * @author Juan Martin
 * 
 */
public class Departamento {
	String id;
	String nombreDep;
	int capacidadTrabajadores;
	
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
	
	
}
