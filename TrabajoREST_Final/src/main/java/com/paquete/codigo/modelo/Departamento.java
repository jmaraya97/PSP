package com.paquete.codigo.modelo;

/**
 * 
 * @author Juan Martin
 * Clase POJO
 */
public class Departamento {
	String id;
	String nombreDep;
	int capacidadTrabajadores;
	
	
	public String getId() {
		return id;
	}
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
