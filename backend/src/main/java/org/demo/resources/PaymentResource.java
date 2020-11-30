package org.demo.resources;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.demo.domain.CheckoutSummary;
import org.demo.dtos.PaymentRequest;
import org.demo.models.User;
import org.demo.services.AuthService;
import org.demo.services.PaymentService;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

@Path("payment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SecurityScheme(securitySchemeName = "jwt", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "jwt")
public class PaymentResource {

    @Inject
    AuthService authService;

    @Inject
    PaymentService paymentService;
    
    @POST
    @Path("checkout")
    @RolesAllowed({"customer"})
    @SecurityRequirement(name = "jwt", scopes = {})
    public CheckoutSummary initiate(PaymentRequest request) {
        return paymentService.intiate(request.getServiceId(), request.getQuantity(), request.getAmount());
    }

    @POST
    @Path("proceed")
    @RolesAllowed({"customer"})
    @SecurityRequirement(name = "jwt", scopes = {})
    public boolean execute(PaymentRequest request) {
        User user = authService.user();
        return paymentService.execute(user, request.getServiceId(), request.getQuantity(), request.getAmount());
    }
}
