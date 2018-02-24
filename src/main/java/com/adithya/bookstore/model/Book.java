package com.adithya.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Book {

    @Id @GeneratedValue
    private Long id;

    private String title;

    private String description;

    private float unitcost;

    private String isbn;

    private Date publishedDate;

    private Integer noOfPages;












}
