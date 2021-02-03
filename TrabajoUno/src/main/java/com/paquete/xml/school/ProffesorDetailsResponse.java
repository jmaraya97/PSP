//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.02.03 a las 06:06:17 PM CET 
//


package com.paquete.xml.school;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Proffesor" type="{http://www.paquete.com/xml/school}Proffesor"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "proffesor"
})
@XmlRootElement(name = "ProffesorDetailsResponse")
public class ProffesorDetailsResponse {

    @XmlElement(name = "Proffesor", required = true)
    protected Proffesor proffesor;

    /**
     * Obtiene el valor de la propiedad proffesor.
     * 
     * @return
     *     possible object is
     *     {@link Proffesor }
     *     
     */
    public Proffesor getProffesor() {
        return proffesor;
    }

    /**
     * Define el valor de la propiedad proffesor.
     * 
     * @param value
     *     allowed object is
     *     {@link Proffesor }
     *     
     */
    public void setProffesor(Proffesor value) {
        this.proffesor = value;
    }

}
