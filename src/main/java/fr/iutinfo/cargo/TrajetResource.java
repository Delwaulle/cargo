package fr.iutinfo.cargo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class TrajetResource {
	private static Map<Integer, Trajet> trajets = new HashMap<>();
	
	@POST
	public Trajet createUser(Trajet trajet) {
		int id = trajets.size();
		trajet.setIdtrajet(id+1);
		trajets.put(trajet.getIdtrajet(), trajet);
		return trajet;
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteUser(@PathParam("id") Integer id) {
		if (trajets.containsKey(id)) {
			return Response.accepted().status(Status.ACCEPTED).build();
		}
	    return Response.accepted().status(Status.NOT_FOUND).build();
	}
	
	protected Trajet find(String villeDepart) {
		Trajet out = null;
		for (Trajet t : trajets.values()) {
			if (t.getVilleDepart().equals(villeDepart)) {
				return t;
			}
		}
		return out;
	}
	protected Trajet find(int id) {
		return trajets.get(id);
	}
	
	@GET
	@Path("/{name}")
	public Trajet getUser(@PathParam("name") String villeDepart ) {
		Trajet out = find(villeDepart);
		if (out == null) {
			throw new WebApplicationException(404);
		} 
		return out;
	}
	
	@GET
	public List<Trajet> getUsers(@DefaultValue("10") @QueryParam("limit") int limit) {
		return new ArrayList<Trajet>(trajets.values());
	}

}
