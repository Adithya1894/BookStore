package com.adithya.bookstore.repository;

import com.adithya.bookstore.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BookRepository {

    @PersistenceContext(name = "bookStorePu")
    private EntityManager entityManager;

    public Book find(Long id){

        return  entityManager.find(Book.class, id);

    }

    public Book create(Book book)
    {
         entityManager.persist(book);
         return book;

    }

    public void delete(Long id){

        entityManager.remove(entityManager.getReference(Book.class, id));

    }

}
