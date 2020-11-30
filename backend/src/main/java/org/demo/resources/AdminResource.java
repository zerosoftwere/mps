package org.demo.resources;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
    @Path("users")
    @RolesAllowed({"admin"})
    @SecurityRequirement(name = "jwt", scopes = {})
    public String create(UserCreateRequest request) {
        User user = userService.create(request.getEmail(), request.getPassword(), request.getRole());
        return user.email;
    }

    @GET
    @Path("transactions")
    @RolesAllowed({"admin", "support"})
    @SecurityRequirement(name = "jwt", scopes = {})
    public List<User> transactions() {
        return Transaction.findAll().list();
    }

    @POST
    @Path("vendors")
    @RolesAllowed({"admin"})
    @SecurityRequirement(name = "jwt", scopes = {})
    public List<Vendor> list() {
        return Vendor.findAll().list();
    }

    @POST
    @Path("vendors")
    @RolesAllowed({"admin"})
    @SecurityRequirement(name = "jwt", scopes = {})
    public Vendor create(VendorCreateRequest request) {
        return vendorService.create(request.getName());
    }

    @PUT
    @Path("vendors/{id}")
    @RolesAllowed({"admin"})
    @SecurityRequirement(name = "jwt", scopes = {})
    public Vendor update(@PathParam("id") Long id, VendorCreateRequest request) {
        return vendorService.update(id, request.getName());
    }

    @DELETE
    @Path("vendors/{id}")
    @RolesAllowed({"admin"})
    @SecurityRequirement(name = "jwt", scopes = {})
    public void delete(@PathParam("id") Long id) {
        vendorService.delete(id);
    }

    @GET
    @Path("complaints")
    @RolesAllowed({"admin", "support"})
    @SecurityRequirement(name = "jwt", scopes = {})
    public List<Complaint> complaints() {
        return Complaint.findAll().list();
    }

    @POST
    @Path("complaints")
    @RolesAllowed({"admin", "support"})
    @SecurityRequirement(name = "jwt", scopes = {})
    public void closeComplaint(@PathParam("id") Long id, ComplaintCloseRequest request) {
        User user = AuthService.user();
        complaintService.close(id, request.getResolution(), user);
    }
}
