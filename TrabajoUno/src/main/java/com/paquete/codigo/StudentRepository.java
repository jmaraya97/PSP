package com.paquete.codigo;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import com.paquete.xml.school.Student;
 
@Component
public class StudentRepository {
    private static final Map<String, Student> students = new HashMap<>();
   
    @PostConstruct
    public void initData() {
         
        Student student = new Student();
        student.setName("Sajal");
        student.setStandard(5);
        student.setAddress("Pune");
        student.setProffesor(ProffesorRepository.getProffesormap().get("Paco"));
        students.put(student.getName(), student);
         
        student = new Student();
        student.setName("Kajal");
        student.setStandard(5);
        student.setAddress("Chicago");
        student.setProffesor(ProffesorRepository.getProffesormap().get("Juan"));
        students.put(student.getName(), student);
         
        student = new Student();
        student.setName("Lokesh");
        student.setStandard(6);
        student.setAddress("Delhi");
        student.setProffesor(ProffesorRepository.getProffesormap().get("Pedro"));
        students.put(student.getName(), student);
         
        student = new Student();
        student.setName("Sukesh");
        student.setStandard(7);
        student.setAddress("Noida");
        student.setProffesor(ProffesorRepository.getProffesormap().get("Juan"));
        students.put(student.getName(), student);
    }
 
    public Student findStudent(String name) {
        Assert.notNull(name, "The Student's name must not be null");
        return students.get(name);
    }
}
