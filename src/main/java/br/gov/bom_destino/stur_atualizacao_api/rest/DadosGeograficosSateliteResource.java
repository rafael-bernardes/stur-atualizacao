package br.gov.bom_destino.stur_atualizacao_api.rest;

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

import br.gov.bom_destino.stur_atualizacao_api.utils.PropertiesUtil;

@Path("dados-geograficos-satelite")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DadosGeograficosSateliteResource implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@POST
	public Response atualizar() throws IllegalArgumentException, NullPointerException, IOException {
		ResteasyClient client = new ResteasyClientBuilder().build();
		
		WebTarget target = client.target(PropertiesUtil.obterURI("gateway-api")).path("mensageria-satelite").queryParam("nome-cliente", "stur-atualizacao-api");
		
		Response response = target.request().get();
		
		String resposta = "Dados provenientes do sat�lite atualizados na base do STUR";
		
		if(!Family.SUCCESSFUL.equals(response.getStatusInfo().getFamily())) {
			resposta = "Falha ao consumir mensagem para atualiza��o dos dados provenientes de sat�lite";
		}
		
		return Response.ok().entity(resposta).build();
	}
}