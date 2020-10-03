package com.piotr.restful.endpoint;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.piotr.restful.database.UserDAO;
import com.piotr.restful.model.User;

@RequestScoped
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserEndpoint {

    @Inject
    private UserDAO dao;

    @GET
    public Response listUsers() {
        return Response.ok(dao.getAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getTodo(@PathParam("id") int id) {
        return Response.ok(dao.findById(id)).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") int id, User user) {
        User updateUser = (User) dao.findById(id);

        updateUser.setEmail(user.getEmail());
        updateUser.setUsername(user.getUsername());
        dao.update(updateUser);

        return Response.ok().build();
    }

    @POST
    public Response create(User user) {
        dao.create(user);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) {
        User getUser = (User) dao.findById(id);

        dao.delete(getUser);

        return Response.ok().build();
    }

}