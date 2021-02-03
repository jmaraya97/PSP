package com.paquete.codigo;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
 

/**
 * 
 * @author Juan Martin
 * Clase de configuracion
 */
@EnableWs
@Configuration
public class Config extends WsConfigurerAdapter 
{
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) 
    {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/service/*");
    }
 
    @Bean(name = "EmpresaWsdl") //Nombre que usaremos para acceder al servicio
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) 
    {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("EmpresaDetailsPort");
        wsdl11Definition.setLocationUri("/service/Empresa-details");
        wsdl11Definition.setTargetNamespace("http://www.paquete.com/xml/empresa"); //importante indicar bien el nombre del xsd
        wsdl11Definition.setSchema(countriesSchema);
        return wsdl11Definition;
    }
 
    @Bean
    public XsdSchema countriesSchema() 
    {
        return new SimpleXsdSchema(new ClassPathResource("empresa.xsd"));//importante indicar bien el nombre del xsd
    }
}