package com.adithya.bookstore.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@ApiModel(description = "Book Resource")
public class Book {
    //default constructor
    public Book() {
    }

    @Id @GeneratedValue
    @ApiModelProperty("Primary Identifier")
    private Long id;


    @Column(length = 200)
    @NotNull
    @Size(min=1, max = 200 )
    @ApiModelProperty("title")
    private String title;

    @Column(length = 1000)
    @Size(min=1, max = 100000)
    @ApiModelProperty("Description")
    private String description;

    @Column(name = "unit_cost")
    @Min(1)
    @ApiModelProperty("Cost")
    private float unitcost;

    @NotNull
    @Size(min =1, max = 50)
    @ApiModelProperty("ISBN")
    private String isbn;

    @Column(name = "publication_date")
    @Temporal(TemporalType.DATE)
    @Past
    @ApiModelProperty("Published_Date")
    private Date publishedDate;

    @Column(name = "nb_of_pages")
    @ApiModelProperty("PageCount")
    private Integer noOfPages;

    @ApiModelProperty("Image Url")
    private String image_url;

    @ApiModelProperty("language")
    private Language language;

    public Book(String id, String isbn, String title, String image_url,Language language,float unit_cost,Integer noOfPages, Date publishedDate, String description) {
        this.title = title;
        this.description = description;
        this.unitcost = unit_cost;
        this.isbn = isbn;
        this.publishedDate = publishedDate;
        this.noOfPages = noOfPages;
        this.image_url = image_url;
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
        return image_url;
    }

    public void setImage(String image) {
        this.image_url = image;
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
                ", image='" + image_url + '\'' +
                ", language=" + language +
                '}';
    }
}
