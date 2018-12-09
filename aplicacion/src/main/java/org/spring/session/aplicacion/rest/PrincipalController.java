package org.spring.session.aplicacion.rest;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.session.aplicacion.sesion.SpringSessionClientHttpRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PrincipalController {

	private final Logger slf4jLogger = LoggerFactory.getLogger(PrincipalController.class); 
	
	@Autowired
	RestTemplateFactory restFactory;
	
	@GetMapping("/hello")
	public String sayHello(HttpSession sesion, HttpServletRequest request) {
		
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()){
			String nombreCabecera = headerNames.nextElement();
			//System.out.println("Cabecera:" + nombreCabecera + " - " + request.getHeader(nombreCabecera));
			slf4jLogger.info("Cabecera:" + nombreCabecera + " - " + request.getHeader(nombreCabecera));
		}
		
		sesion.setAttribute("PruebaAtributo2", "otro atributo de prueba");
		/*
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new SpringSessionClientHttpRequestInterceptor());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Cookie","SESSION="+sesion.getId());*/
		/*ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/comun-0.0.1-SNAPSHOT/hola",
				HttpMethod.GET,
			      new HttpEntity<String>(headers),
			      String.class);
		*/
		Enumeration<String> attributeNames = sesion.getAttributeNames();
		while(attributeNames.hasMoreElements()){
			String nombreAtributo = attributeNames.nextElement();
			//System.out.println(nombreAtributo + ":" + sesion.getAttribute(nombreAtributo));
			slf4jLogger.info(nombreAtributo + ":" + sesion.getAttribute(nombreAtributo));
		}
		
		String resultado = restFactory.obtenerTemplate().getForObject("http://localhost:8080/comun-0.0.1-SNAPSHOT/hola", String.class);
		
		return "Id Session local: " + sesion.getId() + " Id Session servicio externo: " + resultado;
	}
	
}
