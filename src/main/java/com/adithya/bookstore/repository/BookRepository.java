package com.adithya.bookstore.repository;

import com.adithya.bookstore.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

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

    public List<Book> findAll(){
        TypedQuery<Book> query = entityManager.createQuery("SELECT b from Book b order by b.title desc ", Book.class);
                return query.getResultList();
    }

    public Long countAll(){
        TypedQuery<Long> query = entityManager.createQuery("SELECT count(b) from Book b", Long.class);
        return query.getSingleResult();

    }



}
