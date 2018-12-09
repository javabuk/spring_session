package org.spring.session.aplicacion.rest;

import org.spring.session.aplicacion.sesion.SpringSessionClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateFactory {

	public RestTemplate obtenerTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new SpringSessionClientHttpRequestInterceptor());
		return restTemplate;
	}
}
