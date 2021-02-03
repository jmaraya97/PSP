package com.paquete.codigo;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import com.paquete.xml.empresa.Empleado;
 
/**
 * 
 * @author Juan Martin
 * Clase que hace la funcion de Base de datos de la tabla Empleado
 */
@Component
public class EmpleadoRepository {
	//Map para almacenar los empleados
    private static final Map<String, Empleado> empleados = new HashMap<>();
 
    @PostConstruct
    public void initData() {
         
    	Empleado empleado = new Empleado();
        empleado.setNombre("Juan");
        empleado.setEdad(25);
        empleado.setDireccion("Sevilla");
        empleado.setDepartamento(DepartamentoRepository.getDepartamentos().get("Recursos Humanos"));
        empleados.put(empleado.getNombre(), empleado);
        
        empleado=new Empleado();
        empleado.setNombre("Paco");
        empleado.setEdad(34);
        empleado.setDireccion("Huelva");
        empleado.setDepartamento(DepartamentoRepository.getDepartamentos().get("Desarrollo"));
        empleados.put(empleado.getNombre(), empleado);
         
        empleado=new Empleado();
        empleado.setNombre("Jose");
        empleado.setEdad(50);
        empleado.setDireccion("Cadiz");
        empleado.setDepartamento(DepartamentoRepository.getDepartamentos().get("Desarrollo"));
        empleados.put(empleado.getNombre(), empleado);
         
        empleado=new Empleado();
        empleado.setNombre("Carlos");
        empleado.setEdad(35);
        empleado.setDireccion("Sevilla");
        empleado.setDepartamento(DepartamentoRepository.getDepartamentos().get("Comercial"));
        empleados.put(empleado.getNombre(), empleado);
    }
 
    //Metodo para buscar un empleado por su nombre
    public Empleado findEmpleado(String name) {
        Assert.notNull(name, "El empleado no puede ser nulo");
        return empleados.get(name);
    }
}
