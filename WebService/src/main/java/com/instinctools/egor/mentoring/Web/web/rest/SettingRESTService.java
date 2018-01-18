package com.instinctools.egor.mentoring.Web.web.rest;


import com.instinctools.egor.mentoring.Web.factories.StorageType;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/settings")
public class SettingRESTService {

    private StorageType currentStorageType = StorageType.MYSQL;

    @GET
    @Path("/getStorageType")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCurrStorageType() {
        return currentStorageType.toString();
    }

    @POST
    @Path("/setStorageType/{type}")
    public void setStorageType(@PathParam("type") StorageType type) {
        currentStorageType = type;
    }

    public StorageType getStorageType() {
        return currentStorageType;
    }


}
