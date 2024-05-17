package dev.asce;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/update/{movieToUpdate}")
    public Response updateMovie(
            @PathParam("movieToUpdate") String movieToUpdate,
            @QueryParam("movie") String updatedMovie){
        //This line converts the movies list into a stream. Streams are used to perform operations on collections in a functional style.
        movies = movies.stream().map(movie -> {
            if(movie.equals(movieToUpdate)){
                return updatedMovie;
            } else {
                return movie;
            }
        }).collect(Collectors.toList());
        return Response.ok(movies).build();
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/delete/{movieToDelete}")
    public Response deleteMovie(@PathParam("movieToDelete") String movieToDelete){
        movies.remove(movieToDelete);
        return Response.ok(movies).build();
    }
}
