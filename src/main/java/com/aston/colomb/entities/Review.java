package com.aston.colomb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime date;
    private Integer nombreEtoiles;
    private Integer nombreLikes;
    private String contenu;
    private Boolean estSignale;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "compte_id", referencedColumnName = "id")
    private Compte compte; // ManyToOne Un Review est fait par un seul Compte

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL) // Deleting event Deletes all its reviews
    @JoinColumn(name = "evenement_id", referencedColumnName = "id")
    private Evenement evenement; // ManyToOne Un Review concerne qu'un seul Evenenement


    // ------------------------- CONSTRUCTEURS  --------------------------
    public Review() {
    }

    public Review(LocalDateTime date, Integer nombreEtoiles, Integer nombreLikes, String contenu, Boolean estSignale) {
        this.date = date;
        this.nombreEtoiles = nombreEtoiles;
        this.nombreLikes = nombreLikes;
        this.contenu = contenu;
        this.estSignale = estSignale;
    }

    public Review(Integer id, LocalDateTime date, Integer nombreEtoiles, Integer nombreLikes, String contenu, Boolean estSignale) {
        this.id = id;
        this.date = date;
        this.nombreEtoiles = nombreEtoiles;
        this.nombreLikes = nombreLikes;
        this.contenu = contenu;
        this.estSignale = estSignale;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getNombreEtoiles() {
        return nombreEtoiles;
    }

    public void setNombreEtoiles(Integer nombreEtoiles) {
        this.nombreEtoiles = nombreEtoiles;
    }

    public Integer getNombreLikes() {
        return nombreLikes;
    }

    public void setNombreLikes(Integer nombreLikes) {
        this.nombreLikes = nombreLikes;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Boolean getEstSignale() {
        return estSignale;
    }

    public void setEstSignale(Boolean estSignale) {
        this.estSignale = estSignale;
    }


    // ---------------- RELATIONSHIP GETTERS SETTERS ----------------
    public Evenement getEvenement() {
        return evenement;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    // ------------------------- TO STRING --------------------------
    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", date=" + date +
                ", nombreEtoiles=" + nombreEtoiles +
                ", nombreLikes=" + nombreLikes +
                ", contenu='" + contenu + '\'' +
                ", estSignale=" + estSignale +
                ", compte=" + compte +
                '}';
    }
}
