package org.demo.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.demo.models.Vendor;
import org.demo.services.VendorService;


@Path("vendors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VendorResource {

    @Inject
    VendorService vendorService;
    
    @GET
    public List<Vendor> list() {
        return Vendor.findAll().list();
    }

    @GET
    @Path("{id}")
    public Vendor retrieve(@PathParam("id") Long id) {
        return vendorService.get(id);
    }
}
