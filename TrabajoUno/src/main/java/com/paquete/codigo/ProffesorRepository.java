package com.paquete.codigo;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import com.paquete.xml.school.Proffesor;

@Component
public class ProffesorRepository {

	private static final Map<String, Proffesor> proffesorMap = new HashMap<>();
	 
    public static Map<String, Proffesor> getProffesormap() {
		return proffesorMap;
	}

	@PostConstruct
    public void initData() {
         
    	Proffesor proffesor = new Proffesor();
        proffesor.setName("Paco");
        proffesor.setAge(40);
        proffesor.setAddress("Sevilla");
        proffesorMap.put(proffesor.getName(), proffesor);
         
        proffesor = new Proffesor();
        proffesor.setName("Juan");
        proffesor.setAge(50);
        proffesor.setAddress("Cordoba");
        proffesorMap.put(proffesor.getName(), proffesor);
         
        proffesor = new Proffesor();
        proffesor.setName("Pedro");
        proffesor.setAge(60);
        proffesor.setAddress("Huelva");
        proffesorMap.put(proffesor.getName(), proffesor);
         
        proffesor = new Proffesor();
        proffesor.setName("Alberto");
        proffesor.setAge(70);
        proffesor.setAddress("Jaen");
        proffesorMap.put(proffesor.getName(), proffesor);
    }
 
    public Proffesor findProffesor(String name) {
        Assert.notNull(name, "The Proffesor's name must not be null");
        return proffesorMap.get(name);
    }
}
