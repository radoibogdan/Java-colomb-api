package com.aston.colomb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private Date date;
    private Date heureOuverture;
    private Date heureFermeture;
    private Float prix;
    private String adresse;
    private String categorie;
    private String photo;
    private String description;
    private Integer nombrePersMax;
    private Integer nombreLikes;
    private Integer nombreVues;
    private Boolean estSignale;
    private Boolean estSuspendu;
    private double longitude;
    private double latitude;

    @JsonIgnore
    @ManyToMany(mappedBy = "evenementsLiked")
    private Set<Compte> likedByComptes = new HashSet<>();

    @OneToMany(mappedBy = "evenement") // OneToMany Un Evenement peut avoir plusieurs avis
    private Set<Review> reviews = new HashSet<>();

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "compte_id", referencedColumnName = "id")
    private Compte compte; // ManyToOne Un Evenement est crée par un seul Compte



    // ------------------------- CONSTRUCTEURS  --------------------------
    public Evenement() {
    }

    public Evenement(String nom, Date date, Date heureOuverture, Date heureFermeture, Float prix, String adresse, String categorie, String photo, String description, Integer nombrePersMax, Integer nombreLikes, Integer nombreVues, Boolean estSignale, Boolean estSuspendu, double longitude, double latitude) {
        this.nom = nom;
        this.date = date;
        this.heureOuverture = heureOuverture;
        this.heureFermeture = heureFermeture;
        this.prix = prix;
        this.adresse = adresse;
        this.categorie = categorie;
        this.photo = photo;
        this.description = description;
        this.nombrePersMax = nombrePersMax;
        this.nombreLikes = nombreLikes;
        this.nombreVues = nombreVues;
        this.estSignale = estSignale;
        this.estSuspendu = estSuspendu;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Evenement(Integer id, String nom, Date date, Date heureOuverture, Date heureFermeture, Float prix, String adresse, String categorie, String photo, String description, Integer nombrePersMax, Integer nombreLikes, Integer nombreVues, Boolean estSignale, Boolean estSuspendu, double longitude, double latitude) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.heureOuverture = heureOuverture;
        this.heureFermeture = heureFermeture;
        this.prix = prix;
        this.adresse = adresse;
        this.categorie = categorie;
        this.photo = photo;
        this.description = description;
        this.nombrePersMax = nombrePersMax;
        this.nombreLikes = nombreLikes;
        this.nombreVues = nombreVues;
        this.estSignale = estSignale;
        this.estSuspendu = estSuspendu;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getHeureOuverture() {
        return heureOuverture;
    }

    public void setHeureOuverture(Date heureOuverture) {
        this.heureOuverture = heureOuverture;
    }

    public Date getHeureFermeture() {
        return heureFermeture;
    }

    public void setHeureFermeture(Date heureFermeture) {
        this.heureFermeture = heureFermeture;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNombrePersMax() {
        return nombrePersMax;
    }

    public void setNombrePersMax(Integer nombrePersMax) {
        this.nombrePersMax = nombrePersMax;
    }

    public Integer getNombreLikes() {
        return nombreLikes;
    }

    public void setNombreLikes(Integer nombreLikes) {
        this.nombreLikes = nombreLikes;
    }

    public Integer getNombreVues() {
        return nombreVues;
    }

    public void setNombreVues(Integer nombreVues) {
        this.nombreVues = nombreVues;
    }

    public Boolean getEstSignale() {
        return estSignale;
    }

    public void setEstSignale(Boolean estSignale) {
        this.estSignale = estSignale;
    }

    public Boolean getEstSuspendu() {
        return estSuspendu;
    }

    public void setEstSuspendu(Boolean estSuspendu) {
        this.estSuspendu = estSuspendu;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    // ---------------- RELATIONSHIP GETTERS SETTERS ----------------
    public Set<Review> getReviews() {
        return reviews;
    }

    public Set<Compte> getLikedByComptes() {
        return likedByComptes;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }



    // ------------------------- TO STRING --------------------------
    @Override
    public String toString() {
        return "Evenement{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", date=" + date +
                ", heureOuverture=" + heureOuverture +
                ", heureFermeture=" + heureFermeture +
                ", prix=" + prix +
                ", adresse='" + adresse + '\'' +
                ", categorie='" + categorie + '\'' +
                ", photo='" + photo + '\'' +
                ", description='" + description + '\'' +
                ", nombrePersMax=" + nombrePersMax +
                ", nombreLikes=" + nombreLikes +
                ", nombreVues=" + nombreVues +
                ", estSignale=" + estSignale +
                ", estSuspendu=" + estSuspendu +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
