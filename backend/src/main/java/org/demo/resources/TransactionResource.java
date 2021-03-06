package org.demo.resources;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.demo.models.Transaction;
import org.demo.models.User;
import org.demo.services.AuthService;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

@Path("transactions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SecurityScheme(securitySchemeName = "jwt", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "jwt")
public class TransactionResource {

    @Inject
    AuthService authService;
    
    @GET
    @RolesAllowed({"customer"})
    @SecurityRequirement(name = "jwt", scopes = {})
    public List<Transaction> list() {
        User user = authService.user();
        return Transaction.find("user", user).list();
    }
}
