package org.mvn.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class Booksvo extends ResourceSupport  implements  Serializable {
    private static final long serialVersionUID=1L;


    @Mapping("id")
    @JsonProperty("id")
    private int key;

    private String author;

    private String launch_date;

    private String title;


    private String price;

    public Booksvo(){

    }

    public int getKey() {
        return key;
    }

    public void setKey(int id) {
        this.key = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLaunch_date() {
        return launch_date;
    }

    public void setLaunch_date(String launch_date) {
        this.launch_date = launch_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Booksvo booksvo = (Booksvo) o;
        return key == booksvo.key &&
                author.equals(booksvo.author) &&
                launch_date.equals(booksvo.launch_date) &&
                title.equals(booksvo.title) &&
                price.equals(booksvo.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), key, author, launch_date, title, price);
    }
}
