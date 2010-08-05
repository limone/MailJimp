/**
 * Copyright 2010 Michael Laccetti
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package mc4j.service;

import java.io.Serializable;

import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

public interface IMailChimpAPI extends Serializable {
	// Key Management
	@GET
	public String keyList(@QueryParam("output") String output, @QueryParam("method") String method, @QueryParam("username") String username, @QueryParam("password") String password, @QueryParam("apikey") String apikey, @QueryParam("expired") Boolean expired);

	@GET
	public String keyAdd(@QueryParam("output") String output, @QueryParam("method") String method, @QueryParam("username") String username, @QueryParam("password") String password, @QueryParam("apikey") String apikey);
	
	@GET
	public String keyExpire(@QueryParam("output") String output, @QueryParam("method") String method, @QueryParam("username") String username, @QueryParam("password") String password, @QueryParam("apikey") String apikey);
	
	// List Management
	@GET
	public String listAll(@QueryParam("output") String output, @QueryParam("method") String method, @QueryParam("apikey") String apikey);
}