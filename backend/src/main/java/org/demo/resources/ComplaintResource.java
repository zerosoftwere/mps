package org.demo.resources;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.demo.dtos.ComplaintRequest;
import org.demo.dtos.ComplaintResponse;
import org.demo.models.Complaint;
import org.demo.models.User;
import org.demo.services.AuthService;
import org.demo.services.ComplaintService;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

@Path("complaints")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SecurityScheme(securitySchemeName = "jwt", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "jwt")
public class ComplaintResource {

    @Inject
    AuthService authService;

    @Inject
    ComplaintService complaintService;

    @Inject
    
    @GET
    @RolesAllowed("{customer}")
    @SecurityRequirement(name = "jwt", scopes = {})
    public List<Complaint> list() {
        User user = authService.user();
        return Complaint.findByUser(user);
    }

    @POST
    @RolesAllowed("{customer}")
    @SecurityRequirement(name = "jwt", scopes = {})
    public ComplaintResponse raise(ComplaintRequest request) {
        User user = authService.user();
        Complaint complaint = complaintService.create(user, request.getMessage());
        return new ComplaintResponse(complaint.id.toString());
    }
}
