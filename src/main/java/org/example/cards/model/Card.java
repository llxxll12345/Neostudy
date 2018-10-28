package org.example.cards.model;


import javax.persistence.*;

@Entity
@Table(name = "card")
public class Card {
    private Integer id;

    private String name;
    private String translation;
    private String fileLink;

    public Card(){

    }

    public Card(String name, String translation, String link) {
        this.name = name;
        this.translation = translation;
        this.fileLink = link;
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
        this.fileLink = link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return fileLink;
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
