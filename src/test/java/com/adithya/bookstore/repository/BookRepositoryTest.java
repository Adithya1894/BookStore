package com.adithya.bookstore.repository;

import com.adithya.bookstore.model.Book;
import com.adithya.bookstore.model.Language;
import com.adithya.bookstore.rest.BookEndPoint;
import com.adithya.bookstore.rest.JAXRSConfiguration;
import com.adithya.bookstore.util.IsbnGenerator;
import com.adithya.bookstore.util.NumberGenerator;
import com.adithya.bookstore.util.TextUtil;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class BookRepositoryTest {

    @Inject
    private BookRepository bookRepository;

    @Test(expected = Exception.class)
    public void findWithInvalid(){
        bookRepository.find(null);
    }

    @Test(expected = Exception.class)
    public void createInvalidBook(){
        Book book = new Book( "987", "657", "My java", "image", Language.ENGLISH, 10, 348, new Date(),"contains all java content");
        bookRepository.create(book);
    }

    @Test
    public void create() throws Exception{

        assertEquals(Long.valueOf(0), bookRepository.countAll());
        assertEquals(0, bookRepository.findAll().size());

        //create a book

        Book book = new Book("987", "657", "My Story", "image", Language.ENGLISH, 10, 348, new Date(),"contains all java content");
        bookRepository.create(book);
        Long bookId = book.getId();

        //check the id is not null now

        assertNotNull(bookId);

        //Find created Book
        Book booktitle = bookRepository.find(bookId);

        //check the book found is correct book
        assertEquals("My Story", booktitle.getTitle());
        assertTrue(book.getIsbn().startsWith("14"));

        assertEquals(Long.valueOf(1), bookRepository.countAll());
        assertEquals(1, bookRepository.findAll().size());

        bookRepository.delete(bookId);

        assertEquals(Long.valueOf(0), bookRepository.countAll());
        assertEquals(0, bookRepository.findAll().size());

    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(BookRepository.class)
                .addClass(Book.class)
                .addClass(Language.class)
                .addClass(TextUtil.class)
                .addClass(NumberGenerator.class)
                .addClass(IsbnGenerator.class)
                .addClass(BookEndPoint.class)
                .addClass(JAXRSConfiguration.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
    }

}
