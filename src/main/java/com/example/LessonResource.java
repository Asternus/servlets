package com.example;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Path("/lesson-51")
public class LessonResource {

    @GET
    public InputStream getFile() {
        try {
            File f = new File("C:\\git\\demo2\\src\\main\\resources\\WEB-INF\\classes\\files\\two.html");
            return new FileInputStream(f);
        } catch (FileNotFoundException e) {
            // log the error?
            return null;
        }
    }

}
