package com.adithya.bookstore.repository;

import com.adithya.bookstore.model.Book;
import com.adithya.bookstore.model.Language;
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
        Book book = new Book(null, "This has all the java code", 11,"98765",new Date(), 348, "image.url", Language.English );
        bookRepository.create(book);
    }

    @Test
    public void create() throws Exception{

        assertEquals(Long.valueOf(0), bookRepository.countAll());
        assertEquals(0, bookRepository.findAll().size());

        //create a book

        Book book = new Book("My  Story", "This is the story of my life in US", 10, "98765",new Date(), 348, "image.url", Language.English );
        bookRepository.create(book);
        Long bookId = book.getId();

        //check the id is not null now

        assertNotNull(bookId);

        //Find created Book
        Book booktitle = bookRepository.find(bookId);

        //check the book found is correct book
        assertEquals("My Story", booktitle.getTitle());

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
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
    }

}
