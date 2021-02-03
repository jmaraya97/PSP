//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.02.03 a las 11:42:41 PM CET 
//


package com.paquete.xml.empresa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Departamento complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Departamento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombreDep" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="maxEmpleados" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Departamento", propOrder = {
    "nombreDep",
    "maxEmpleados"
})
public class Departamento {

    @XmlElement(required = true)
    protected String nombreDep;
    protected int maxEmpleados;

    /**
     * Obtiene el valor de la propiedad nombreDep.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreDep() {
        return nombreDep;
    }

    /**
     * Define el valor de la propiedad nombreDep.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreDep(String value) {
        this.nombreDep = value;
    }

    /**
     * Obtiene el valor de la propiedad maxEmpleados.
     * 
     */
    public int getMaxEmpleados() {
        return maxEmpleados;
    }

    /**
     * Define el valor de la propiedad maxEmpleados.
     * 
     */
    public void setMaxEmpleados(int value) {
        this.maxEmpleados = value;
    }

}
