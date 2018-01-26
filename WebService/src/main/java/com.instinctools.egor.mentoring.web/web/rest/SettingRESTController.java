package com.instinctools.egor.mentoring.web.web.rest;


import com.instinctools.egor.mentoring.web.factories.SettingService;
import com.instinctools.egor.mentoring.web.factories.StorageType;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("/settings")
public class SettingRESTController {

    private SettingService service;

    public SettingRESTController(final SettingService service) {
        this.service = service;
    }

    @GET
    @Path("/getStorageType")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCurrStorageType() {
        return service.getCurrentType().toString();
    }

    @POST
    @Path("/setStorageType/{type}")
    public void setStorageType(@PathParam("type") final StorageType type) {
        service.setCurrentType(type);
    }
}
