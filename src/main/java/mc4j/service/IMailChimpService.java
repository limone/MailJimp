package mc4j.service;

import java.io.Serializable;

import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

public interface IMailChimpService extends Serializable {
	@GET
	public String listApiKeys(@QueryParam("output") String output, @QueryParam("method") String method, @QueryParam("username") String username, @QueryParam("password") String password, @QueryParam("apikey") String apikey, @QueryParam("expired") Boolean expired);
}