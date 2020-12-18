package org.demo.resources;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.demo.models.Customer;
import org.demo.models.User;
import org.demo.services.AuthService;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

@Path("/profile")
@Produces(MediaType.APPLICATION_JSON)
@SecurityScheme(securitySchemeName = "jwt", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "jwt")
public class ProfileResource {
    @Inject
    AuthService authService;

    @GET
    @RolesAllowed({"customer"})
    @SecurityRequirement(name = "jwt", scopes = {})
    public Response profile() {
        User user = authService.user();
        return Response.ok(Customer.findByUser(user)).build();
    }

}
