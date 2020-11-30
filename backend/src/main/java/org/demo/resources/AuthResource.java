package org.demo.resources;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.demo.dtos.LoginRequest;
import org.demo.dtos.RegisterRequest;
import org.demo.services.AuthService;

@Path("auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    AuthService authService;
    
    @POST
    @PermitAll
    @Path("login")
    public String login(LoginRequest request) {
        return authService.authenticate(request.getEmail(), request.getPassword());
    }

    @POST
    @PermitAll
    @Path("register")
    public String register(RegisterRequest request) {
        return authService.register(request.getName(), request.getEmail(), request.getPhone(), request.getPassword());
    }
}
