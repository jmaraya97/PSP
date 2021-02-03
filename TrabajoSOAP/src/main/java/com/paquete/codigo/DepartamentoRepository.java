package com.paquete.codigo;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import com.paquete.xml.empresa.Departamento;

/**
 * 
 * @author Juan Martin
 * Esta clase hace la funcion de Base de datos de la tabla Departamento
 */
@Component
public class DepartamentoRepository {

	//Map que hace la funcion de almacenar los datos
    private static final Map<String, Departamento> departamentos = new HashMap<>();

	@PostConstruct
    public void initData() {
         
    	Departamento departamento = new Departamento();
    	departamento.setNombreDep("Recursos Humanos");
    	departamento.setMaxEmpleados(25);
    	departamentos.put(departamento.getNombreDep(), departamento);
         
    	departamento = new Departamento();
    	departamento.setNombreDep("Desarrollo");
    	departamento.setMaxEmpleados(45);
    	departamentos.put(departamento.getNombreDep(), departamento);
    	
    	departamento = new Departamento();
    	departamento.setNombreDep("Comercial");
    	departamento.setMaxEmpleados(4);
    	departamentos.put(departamento.getNombreDep(), departamento);
    }
 
	//Metodo que busca un departamento por su nombre. En caso de que sea Null mostrara un mensaje
    public Departamento findDepartamento(String name) {
        Assert.notNull(name, "El Departamento no puede ser nulo");
        return departamentos.get(name);
    }
    
    //Getter del Map
    public static Map<String, Departamento> getDepartamentos() {
		return departamentos;
	}
}
