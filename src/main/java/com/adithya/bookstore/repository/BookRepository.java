package com.adithya.bookstore.repository;

import com.adithya.bookstore.model.Book;
import com.adithya.bookstore.util.TextUtil;

import javax.enterprise.inject.TransientReference;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

@Transactional(Transactional.TxType.SUPPORTS)
public class BookRepository {

    @PersistenceContext(name = "bookStorePu")
    private EntityManager entityManager;

    @Inject
    private TextUtil textUtil;

    public Book find(@NotNull Long id){

        return  entityManager.find(Book.class, id);

    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Book create(@NotNull Book book)
    {
        //sanitizes the title of the book, if the user enters more than required spaces.
         book.setTitle(textUtil.sanitize(book.getTitle()));
         entityManager.persist(book);
         return book;

    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(@NotNull Long id){

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
