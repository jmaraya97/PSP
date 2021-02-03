package com.paquete.codigo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.paquete.xml.school.ProffesorDetailsRequest;
import com.paquete.xml.school.ProffesorDetailsResponse;
import com.paquete.xml.school.ProffesorAgeRequest;
import com.paquete.xml.school.ProffesorAgeResponse;
 
@Endpoint
public class ProffesorEndpoint 
{
    private static final String NAMESPACE_URI = "http://www.paquete.com/xml/school";
 
    private ProffesorRepository ProffesorRepository;
 
    @Autowired
    public ProffesorEndpoint(ProffesorRepository ProffesorRepository) {
        this.ProffesorRepository = ProffesorRepository;
    }
 
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ProffesorDetailsRequest")
    @ResponsePayload
    public ProffesorDetailsResponse getStudent(@RequestPayload ProffesorDetailsRequest request) {
    	ProffesorDetailsResponse response = new ProffesorDetailsResponse();
        response.setProffesor(ProffesorRepository.findProffesor(request.getName()));
 
        return response;
    }
    
  //CADA VEZ QUE ESTA APLICACION RECIVE UN "ProffesorAgeRequest", SE LANZA ESTE METODO
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ProffesorAgeRequest")
    @ResponsePayload //DEVUELVE UN StudentDetailsResponse
    public ProffesorAgeResponse getProffesor(@RequestPayload ProffesorAgeRequest request) {
    	ProffesorAgeResponse response = new ProffesorAgeResponse();
        response.setAge(ProffesorRepository.findProffesor(request.getName()).getAge());
        return response;
    }
}
