package com.instinctools.egor.mentoring.web.web.rest;


import com.instinctools.egor.mentoring.web.factories.SettingService;
import com.instinctools.egor.mentoring.web.factories.StorageType;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/settings")
public class SettingRESTController {

    private SettingService service;

    public SettingRESTController(SettingService service) {
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
    public void setStorageType(@PathParam("type") StorageType type) {
        service.setCurrentType(type);
    }
}
