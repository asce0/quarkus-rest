package dev.asce;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Path("/movies")
public class MovieResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getMovies() {
        return Movie.listAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/size")
    public long countMovies(){
        return Movie.count();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Movie getMovie(@PathParam("id") long id) {
        return Movie.findById(id);
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON) // it will return a list of movies
    @Consumes(MediaType.APPLICATION_JSON) // because the endpoint will consume a new movie from the request
    @Path("/create")
    public Response createMovie(Movie newMovie){
        Movie.persist(newMovie);
        return Response.ok(newMovie).status(Response.Status.CREATED).build();
    }

    @PUT
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{id}")
    public Response updateMovie(
            @PathParam("id") long id,
            Movie updatedMovie){
        Movie movie = Movie.findById(id);
        if (movie == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        movie.title = updatedMovie.title;
        return Response.ok(movie).build();
    }

    @DELETE
    @Transactional
    @Path("/delete/{id}")
    public Response deleteMovie(@PathParam("id") long id){
        boolean deleted = Movie.deleteById(id);
        if (deleted) {
            return Response.noContent().build();
        } else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
