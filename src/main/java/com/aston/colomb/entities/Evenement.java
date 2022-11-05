package com.aston.colomb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(min = 5, max = 40, message = "Le nom de l'événement doit avoir entre 5 et 40 caractères")
    private String nom;
    @FutureOrPresent(message = "L'événement ne peut être créé que dans le futur ou pour aujourd'hui.")
    private LocalDateTime date;
    private LocalDateTime heureOuverture;
    private LocalDateTime heureFermeture;
    @PositiveOrZero(message = "Le prix de l'événement ne peut pas être négatif.")
    private Float prix;
    @Size(min = 10, max = 100, message = "L'adresse de l'événement doit avoir doit avoir entre 10 et 100 caractères.")
    private String adresse;
    private String categorie;
    private String photo;
    @Size(min = 10, max = 500, message = "La description de l'événement doit avoir entre 10 et 500 caractères.")
    private String description;
    @Positive(message = "Le nombre de personnes qui peuvent accéder à l'événement ne peut pas être négatif ou zéro.")
    private Integer nombrePersMax;
    private Integer nombreLikes;
    private Integer nombreVues;
    private Boolean estSignale;
    private Boolean estSuspendu;
    private double longitude;
    private double latitude;
    private Integer idApiParis;

    @JsonIgnore
    @ManyToMany(mappedBy = "evenementsLiked")
    private Set<Compte> likedByComptes = new HashSet<>();

    @OneToMany(mappedBy = "evenement", cascade = CascadeType.ALL) // OneToMany Un Evenement peut avoir plusieurs avis
    private Set<Review> reviews = new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "compte_id", referencedColumnName = "id")
    private Compte compte; // ManyToOne Un Evenement est crée par un seul Compte



    // ------------------------- CONSTRUCTEURS  --------------------------
    public Evenement() {
    }

    public Evenement(String nom, LocalDateTime date, LocalDateTime heureOuverture, LocalDateTime heureFermeture, Float prix, String adresse, String categorie, String photo, String description, Integer nombrePersMax, Integer nombreLikes, Integer nombreVues, Boolean estSignale, Boolean estSuspendu, double longitude, double latitude, Integer idApiParis) {
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
        this.idApiParis = idApiParis;
    }

    public Evenement(Integer id, String nom, LocalDateTime date, LocalDateTime heureOuverture, LocalDateTime heureFermeture, Float prix, String adresse, String categorie, String photo, String description, Integer nombrePersMax, Integer nombreLikes, Integer nombreVues, Boolean estSignale, Boolean estSuspendu, double longitude, double latitude, Integer idApiParis) {
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
        this.idApiParis = idApiParis;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getHeureOuverture() {
        return heureOuverture;
    }

    public void setHeureOuverture(LocalDateTime heureOuverture) {
        this.heureOuverture = heureOuverture;
    }

    public LocalDateTime getHeureFermeture() {
        return heureFermeture;
    }

    public void setHeureFermeture(LocalDateTime heureFermeture) {
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

    public Integer getIdApiParis() {
        return idApiParis;
    }

    public void setIdApiParis(Integer idApiParis) {
        this.idApiParis = idApiParis;
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
                ", idApiParis=" + idApiParis +
                '}';
    }
}
