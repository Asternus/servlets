package com.example;

import Entity.Device;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import repository.DeviceRepository;

@Path("/hello-world")
public class HelloResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @POST
    @Path("/{name}")
    @Produces("text/plain")
    public String customWelcome(@PathParam("name") String name) {
        return "Welcome, " + name;
    }

    @GET
    @Path("/{id}")
    @Produces("text/plain")
    public String customWelcome(@PathParam("id") int id) {
        final Device deviceById = DeviceRepository.getDeviceById(id);
        return "Device, " + id + " " + deviceById;
    }
}