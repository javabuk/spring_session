package org.spring.session.aplicacion.sesion;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
/**
 * Clase que intercepta la llamada http y le añade el id de session en un parámetro de la cabecera.
 * Asi, de forma transparente, añadimos el identificativo de la sesión para Spring Session en todas las llamadas
 * @author Jorge
 *
 */
@Component
public class SpringSessionClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		request.getHeaders().add("X-Auth-Token", RequestContextHolder.getRequestAttributes().getSessionId());		
		return execution.execute(request, body);
	}

}
