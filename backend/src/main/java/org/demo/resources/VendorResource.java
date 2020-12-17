package org.demo.resources;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.demo.models.Vendor;
import org.demo.services.VendorService;


@Path("vendors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VendorResource {

    @Inject
    VendorService vendorService;
    
    @GET
    @PermitAll
    public Response list() {
        return Response.ok(Vendor.findAll().list()).build();
    }

    @GET
    @PermitAll
    @Path("{id}")
    public Response retrieve(@PathParam("id") Long id) {
        return Response.ok(vendorService.get(id).getServices()).build();
    }
}
