package org.demo.services;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;

import org.demo.models.Vendor;

@ApplicationScoped
public class VendorService {
    public Vendor create(String name) {
        Vendor vendor = new Vendor();
        vendor.name = name;
        vendor.persist();

        return vendor;
    }

    public Vendor get(Long id) {
        Vendor vendor = Vendor.findById(id);
        if (vendor == null) throw new NotFoundException();
        return vendor;
    }

    public Vendor update(Long id, String name) {
        Vendor vendor = Vendor.findById(id);
        if (vendor == null) throw new NotFoundException();

        vendor.name = name;
        vendor.persist();

        return vendor;
    }

    public void delete(Long id) {
        Vendor vendor = Vendor.findById(id);
        if (vendor == null) throw new NotFoundException();
        
        vendor.delete();
    }
}
