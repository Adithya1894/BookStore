package com.adithya.bookstore.rest;

import com.adithya.bookstore.model.Book;
import com.adithya.bookstore.repository.BookRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/books")
@Api("Books")
public class BookEndPoint {

    @GET
    @Path("{id: \\d+}")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Returns a book if it is available",response = Book.class)
    @ApiResponses({

            @ApiResponse(code = 200, message = "Book Found"),
            @ApiResponse(code = 400, message = "Bad request or invalid data"),
            @ApiResponse(code = 204, message = "no content"),
            @ApiResponse(code = 404, message = "book not found")

    })
    public Response getBook(@PathParam("id")  @Min(1) Long id) {

        Book book = bookRepository.find(id);
        if (book == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(book).build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @ApiOperation(value = "Creates a book with the given details after validation")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Book has been created"),
            @ApiResponse(code = 415, message = "Format Incorrect")
    })
    public Response createBook(Book book, @Context UriInfo uriInfo) {
        book = bookRepository.create(book);
        URI createdURI = uriInfo.getBaseUriBuilder().path(book.getId().toString()).build();
        return Response.created(createdURI).build();
    }

    //Delete request to delete the particular book using its id.
    @DELETE
    @Path("/{id : \\d+}")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Deletes a book with Delete request")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Book Deleted successfully"),
            @ApiResponse(code = 500, message = "Internal Error")
    })
    public Response deleteBook(@PathParam("id") @Min(1) Long id) {
        bookRepository.delete(id);
        return Response.noContent().build();
    }

    //Get request to pull the total books
    @GET
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Returns a list of books if  available",response = Book.class)
    @ApiResponses({

            @ApiResponse(code = 200, message = "Book Found"),
            @ApiResponse(code = 204, message = "no content"),
            @ApiResponse(code = 400, message = "Bad request or invalid data"),
            @ApiResponse(code = 404, message = "book not found")

    })
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
    @ApiOperation(value = "Counts the number of books")
    @ApiResponses({
            @ApiResponse(code = 200, message = "books found"),
            @ApiResponse(code = 204, message = "No Content")
    })
    public Response countBook() {
        Long noOfBooks = bookRepository.countAll();
        if(noOfBooks == 0)
            return Response.noContent().build();

        return Response.ok(noOfBooks).build();
    }

    @Inject
    private BookRepository bookRepository;



}
