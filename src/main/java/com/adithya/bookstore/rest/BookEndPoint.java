package com.adithya.bookstore.rest;

import com.adithya.bookstore.model.Book;
import com.adithya.bookstore.repository.BookRepository;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/books")
public class BookEndPoint {

    @GET
    @Path("{id: \\d+}")
    @Produces(APPLICATION_JSON)
    public Response getBook(@PathParam("id")  @Min(1) Long id) {

        Book book = bookRepository.find(id);
        if (book == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(book).build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response createBook(Book book, @Context UriInfo uriInfo) {
        book = bookRepository.create(book);
        URI createdURI = uriInfo.getBaseUriBuilder().path(book.getId().toString()).build();
        return Response.created(createdURI).build();
    }

    //Delete request to delete the particular book using its id.
    @DELETE
    @Path("/{id : \\d+}")
    @Produces(APPLICATION_JSON)
    public Response deleteBook(@PathParam("id") @Min(1) Long id) {
        bookRepository.delete(id);
        return Response.noContent().build();
    }

    //Get request to pull the total books
    @GET
    @Produces(APPLICATION_JSON)
    public Response getBooks() {

        List<Book> books = bookRepository.findAll();

        if(books.size() == 0)
            return Response.noContent().build();

        return Response.ok(books).build();
   }

    //Get request to count the totatl books available
    @GET
    @Path("/count")
    @Produces(APPLICATION_JSON)
    public Response countBook() {
        Long noOfBooks = bookRepository.countAll();
        if(noOfBooks == 0)
            return Response.noContent().build();

        return Response.ok(noOfBooks).build();
    }

    @Inject
    private BookRepository bookRepository;



}
