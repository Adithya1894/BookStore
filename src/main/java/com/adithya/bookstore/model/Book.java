package com.adithya.bookstore.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Book {

    @Id @GeneratedValue
    private Long id;

    @Column(length = 200)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(name = "unit_cost")
    private float unitcost;

    private String isbn;

    @Column(name = "published_date")
    @Temporal(TemporalType.DATE)
    private Date publishedDate;

    @Column(name = "no_of_pages")
    private Integer noOfPages;

    private String image;

    private Language language;

    public Book(String title, String description, float unitcost, String isbn, Date publishedDate, Integer noOfPages, String image, Language language) {
        this.title = title;
        this.description = description;
        this.unitcost = unitcost;
        this.isbn = isbn;
        this.publishedDate = publishedDate;
        this.noOfPages = noOfPages;
        this.image = image;
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getUnitcost() {
        return unitcost;
    }

    public void setUnitcost(float unitcost) {
        this.unitcost = unitcost;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Integer getNoOfPages() {
        return noOfPages;
    }

    public void setNoOfPages(Integer noOfPages) {
        this.noOfPages = noOfPages;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", unitcost=" + unitcost +
                ", isbn='" + isbn + '\'' +
                ", publishedDate=" + publishedDate +
                ", noOfPages=" + noOfPages +
                ", image='" + image + '\'' +
                ", language=" + language +
                '}';
    }
}
