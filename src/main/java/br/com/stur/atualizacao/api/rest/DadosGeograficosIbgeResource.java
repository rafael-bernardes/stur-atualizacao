package br.com.stur.atualizacao.api.rest;

import java.io.IOException;
import java.io.Serializable;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import br.com.stur.atualizacao.api.utils.PropertiesUtil;

@Path("dados-geograficos-ibge")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DadosGeograficosIbgeResource implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@POST
	public Response atualizar() throws IllegalArgumentException, NullPointerException, IOException {
		ResteasyClient client = new ResteasyClientBuilder().build();
		
		WebTarget target = client.target(PropertiesUtil.obterURI("gateway-api")).path("mensageria-ibge").queryParam("nome-api", "stur-atualizacao-api");
		
		Response response = target.request().get();
		
		String resposta = "Dados provenientes do IBGE atualizados na base do STUR";
		
		if(!Family.SUCCESSFUL.equals(response.getStatusInfo().getFamily())) {
			resposta = "Falha ao consumir mensagem para atualização dos dados provenientes do IBGE";
		}
		
		return Response.ok().entity(resposta).build();
	}
}