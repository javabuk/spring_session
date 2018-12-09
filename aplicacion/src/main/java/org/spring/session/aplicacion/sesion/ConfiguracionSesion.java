package org.spring.session.aplicacion.sesion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfiguracionSesion {

	@Autowired 
	SpringSessionClientHttpRequestInterceptor interceptorSesion;
	
	/**
	 * Bean que se encarga de indicar a Spring Session en que parametro de la cabecera se envía el id de Session
	 * @return
	 */
	@Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        return new HeaderHttpSessionIdResolver("X-Auth-Token");
    }
	
	@Bean
	public RestTemplate obtenerRestTemplate(){ 
	
		RestTemplate restTemplate = new RestTemplate();
		// Añadimos el interceptor que incluye el identificativo de session
		//restTemplate.getInterceptors().add(new SpringSessionClientHttpRequestInterceptor());
		restTemplate.getInterceptors().add(interceptorSesion);
		
		return restTemplate;
	}
	
}
