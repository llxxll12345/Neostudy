package org.example.cards.model;


import javax.persistence.*;

@Entity
@Table(name = "card")
public class Card {
    private Integer id;

    private String name;
    private String translation;
    private String link;

    public Card(){

    }

    public Card(String name, String translation, String link) {
        this.name = name;
        this.translation = translation;
        this.link = link;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public String getTranslation() {
        return translation;
    }

    public String getName() {
        return name;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
