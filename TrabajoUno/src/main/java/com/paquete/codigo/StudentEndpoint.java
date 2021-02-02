package com.paquete.codigo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.paquete.xml.school.StudentDetailsRequest;
import com.paquete.xml.school.StudentDetailsResponse;


//CADA SERVICIO NUEVO QUE QUERAMOS IMPLEMENTAR DEBERA SER UN METODO NUEVO DENTRO DE ESTA CLASE Y EN SCHOOL.XSD
@Endpoint
public class StudentEndpoint 
{
   private static final String NAMESPACE_URI = "http://www.paquete.com/xml/school";

   private StudentRepository StudentRepository;

   @Autowired
   public StudentEndpoint(StudentRepository StudentRepository) {
       this.StudentRepository = StudentRepository;
   }

   //CADA VEZ QUE ESTA APLICACION RECIVE UN "StudentDetailsRequest", SE LANZA ESTE METODO
   @PayloadRoot(namespace = NAMESPACE_URI, localPart = "StudentDetailsRequest")
   @ResponsePayload //DEVUELVE UN StudentDetailsResponse
   public StudentDetailsResponse getStudent(@RequestPayload StudentDetailsRequest request) {
       StudentDetailsResponse response = new StudentDetailsResponse();
       response.setStudent(StudentRepository.findStudent(request.getName()));

       return response;
   }
}