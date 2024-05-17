package dev.asce;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/movies")
public class MovieResource {
    public static List<String> movies = new ArrayList<>();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMovies() {
        return Response.ok(movies).build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/size")
    public Integer countMovies(){
        return movies.size();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN) // it will return a list of movies
    @Consumes(MediaType.TEXT_PLAIN) // because the endpoint will consume a new movie from the request
    @Path("/create")
    public Response createMovie(String newMovie){
        movies.add(newMovie);
        return Response.ok(movies).build();
    }
}
