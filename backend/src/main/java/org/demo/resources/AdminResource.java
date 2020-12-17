package org.demo.resources;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.demo.dtos.ComplaintCloseRequest;
import org.demo.dtos.UserCreateRequest;
import org.demo.dtos.VendorCreateRequest;
import org.demo.models.Complaint;
import org.demo.models.Transaction;
import org.demo.models.User;
import org.demo.models.Vendor;
import org.demo.services.AuthService;
import org.demo.services.ComplaintService;
import org.demo.services.UserService;
import org.demo.services.VendorService;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

@Path("admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SecurityScheme(securitySchemeName = "jwt", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "jwt")
public class AdminResource {

    @Inject
    AuthService AuthService;

    @Inject
    VendorService vendorService;

    @Inject
    UserService userService;

    @Inject
    ComplaintService complaintService;
    
    @GET
    @Path("users")
    @RolesAllowed({"admin", "support"})
    @SecurityRequirement(name = "jwt", scopes = {})
    public List<User> users() {
        return User.findAll().list();
    }

    @POST
    @Transactional
    @Path("users")
    @RolesAllowed({"admin"})
    @SecurityRequirement(name = "jwt", scopes = {})
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(UserCreateRequest request) {
        User user = userService.create(request.getEmail(), request.getPassword(), request.getRole());
        return Response.status(Status.CREATED).entity(user.email).build();
    }

    @GET
    @Transactional
    @Path("transactions")
    @RolesAllowed({"admin", "support"})
    @SecurityRequirement(name = "jwt", scopes = {})
    public Response transactions() {
        return Response.ok(Transaction.findAll().list()).build();
    }

    @GET
    @Path("vendors")
    @RolesAllowed({"admin"})
    @SecurityRequirement(name = "jwt", scopes = {})
    public Response list() {
        return Response.ok(Vendor.findAll().list()).build();
    }

    @POST
    @Transactional
    @Path("vendors")
    @RolesAllowed({"admin"})
    @SecurityRequirement(name = "jwt", scopes = {})
    public Response create(VendorCreateRequest request) {
        return Response.status(Status.CREATED).entity(vendorService.create(request.getName())).build();
    }

    @PUT
    @Transactional
    @Path("vendors/{id}")
    @RolesAllowed({"admin"})
    @SecurityRequirement(name = "jwt", scopes = {})
    public Response update(@PathParam("id") Long id, VendorCreateRequest request) {
        return Response.ok(vendorService.update(id, request.getName())).build();
    }

    @DELETE
    @Transactional
    @Path("vendors/{id}")
    @RolesAllowed({"admin"})
    @SecurityRequirement(name = "jwt", scopes = {})
    public Response delete(@PathParam("id") Long id) {
        vendorService.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("complaints")
    @RolesAllowed({"admin", "support"})
    @SecurityRequirement(name = "jwt", scopes = {})
    public Response complaints() {
        return Response.ok(Complaint.findAll().list()).build();
    }

    @POST
    @Transactional
    @Path("complaints")
    @RolesAllowed({"admin", "support"})
    @SecurityRequirement(name = "jwt", scopes = {})
    public Response closeComplaint(@PathParam("id") Long id, ComplaintCloseRequest request) {
        User user = AuthService.user();
        complaintService.close(id, request.getResolution(), user);
        return Response.noContent().build();
    }
}
