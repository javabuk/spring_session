package org.spring.session.comun.rest;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController()
public class PruebaController {

	private final Logger slf4jLogger = LoggerFactory.getLogger(PruebaController.class); 
	
	
	
	
	@GetMapping("/hola")
	public String sayHello(HttpSession sesion, HttpServletRequest request) {
		
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()){
			String nombreCabecera = headerNames.nextElement();
			//System.out.println("Cabecera:" + nombreCabecera + " - " + request.getHeader(nombreCabecera));
			slf4jLogger.info("Cabecera:" + nombreCabecera + " - " + request.getHeader(nombreCabecera));
		}
		
		RestTemplate restTemplate = new RestTemplate();
		
		//sesion = request.getSession();
		//String resultado = restTemplate.getForObject("https://restcountries.eu/rest/v2/all", String.class);
		Enumeration<String> attributeNames = sesion.getAttributeNames();
		while(attributeNames.hasMoreElements()){
			String nombreAtributo = attributeNames.nextElement();
			//System.out.println(nombreAtributo + ":" + sesion.getAttribute(nombreAtributo));
			slf4jLogger.info(nombreAtributo + ":" + sesion.getAttribute(nombreAtributo));
		}
		System.out.println("prueba");
		return sesion.getId() + " " + request.getSession().getId();
	}

}
