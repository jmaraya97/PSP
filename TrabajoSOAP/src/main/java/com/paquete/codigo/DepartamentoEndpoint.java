package com.paquete.codigo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.paquete.xml.empresa.DepartamentoDetailsRequest;
import com.paquete.xml.empresa.DepartamentoDetailsResponse;

@Endpoint
public class DepartamentoEndpoint {

	private static final String NAMESPACE_URI = "http://www.paquete.com/xml/empresa";
	 
    private DepartamentoRepository DepartamentoRepository;
 
    @Autowired
    public DepartamentoEndpoint(DepartamentoRepository DepartamentoRepository) {
        this.DepartamentoRepository = DepartamentoRepository;
    }
 
    
    /**
     * Metodo que se lanzará cuando la aplicacion reciba una peticion del tipo DepartamentoDetailsRequest.
     * Este metodo devolverá como respuesta el departamento cuyo nombre coincida con uno existente en el Map
     * @param request
     * @return response
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DepartamentoDetailsRequest")
    @ResponsePayload
    public DepartamentoDetailsResponse getDepartamento(@RequestPayload DepartamentoDetailsRequest request) {
    	DepartamentoDetailsResponse response = new DepartamentoDetailsResponse();
        response.setDepartamento(DepartamentoRepository.findDepartamento(request.getNombreDep()));
 
        return response;
    }
}
