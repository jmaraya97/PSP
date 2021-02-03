package com.paquete.codigo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.paquete.xml.empresa.EmpleadoDetailsRequest;
import com.paquete.xml.empresa.EmpleadoDetailsResponse;
import com.paquete.xml.empresa.EmpleadoEdadRequest;
import com.paquete.xml.empresa.EmpleadoEdadResponse;

 
@Endpoint
public class EmpleadoEndpoint 
{
    private static final String NAMESPACE_URI = "http://www.paquete.com/xml/empresa";
 
    private EmpleadoRepository EmpleadoRepository;
 
    @Autowired
    public EmpleadoEndpoint(EmpleadoRepository EmpleadoRepository) {
        this.EmpleadoRepository = EmpleadoRepository;
    }
 
    /**
     * Metodo que se lanzará cuando la aplicacion reciba una peticion del tipo EmpleadoDetailsRequest.
     * Este metodo devolverá como respuesta un empleado cuyo nombre coincida con uno existente en el Map
     * @param request
     * @return response
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "EmpleadoDetailsRequest")
    @ResponsePayload
    public EmpleadoDetailsResponse getEmpleado(@RequestPayload EmpleadoDetailsRequest request) {
    	EmpleadoDetailsResponse response = new EmpleadoDetailsResponse();
        response.setEmpleado(EmpleadoRepository.findEmpleado(request.getNombre()));
 
        return response;
    }
    
    /**
     * Metodo que se lanzará cuando la aplicacion reciba una peticion del tipo EmpleadoEdadRequest.
     * Este metodo devolverá como respuesta la edad del empleado cuyo nombre coincida con uno existente en el Map
     * @param request
     * @return response
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "EmpleadoEdadRequest")
    @ResponsePayload 
    public EmpleadoEdadResponse getEmpleado(@RequestPayload EmpleadoEdadRequest request) {
    	EmpleadoEdadResponse response = new EmpleadoEdadResponse();
        response.setEdad(EmpleadoRepository.findEmpleado(request.getNombre()).getEdad());
        return response;
    }
}
