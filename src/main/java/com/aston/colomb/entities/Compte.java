package com.aston.colomb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Email(message = "L'e-mail n'est pas valide.")
    private String email;
    @Max(value = 30, message = "Le login d'un utilisateur doit avoir maximum 30 caractères.")
    private String login;
    private String passwordHash;
    @Size(min = 2, max = 30, message = "Le nom d'un utilisateur doit avoir entre 2 et 40 caractères")
    private String nom;
    @Size(min = 2, max = 30, message = "Le prénom d'un utilisateur doit avoir entre 2 et 40 caractères")
    private String prenom;
    @Max(value = 50, message = "L'adresse d'un utilisateur doit avoir maximum 50 caractères.")
    private String adresse;
    private Date dob;
    private String photo;
    private String description;
    private String role;
    private Boolean visibiliteReview;
    private Boolean estValide;
    private String numeroSiret;

    @ManyToMany
    @JoinTable(
            name="compte_liker_evenement",
            joinColumns = @JoinColumn(name = "compte_id"),
            inverseJoinColumns = @JoinColumn(name = "evenement_id")
    )
    private Set<Evenement> evenementsLiked = new HashSet<>();

    @OneToMany(mappedBy = "compte") // OneToMany Un Compte peut donner plusieurs Review
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(mappedBy = "compte") // OneToMany Un Compte peut créer plusieurs Evenement
    private Set<Evenement> evenementsCrees = new HashSet<>();


    // ------------------------- CONSTRUCTEURS  --------------------------
    public Compte() {
    }

    public Compte(Integer id, String email, String login, String passwordHash, String nom, String prenom, String role) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.passwordHash = passwordHash;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
    }

    public Compte(String email, String login, String passwordHash, String nom, String prenom, String adresse, Date dob, String photo, String description, String role, Boolean visibiliteReview, Boolean estValide, String numeroSiret, Set<Evenement> evenementsLiked, Set<Review> reviews, Set<Evenement> evenementsCrees) {
        this.email = email;
        this.login = login;
        this.passwordHash = passwordHash;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.dob = dob;
        this.photo = photo;
        this.description = description;
        this.role = role;
        this.visibiliteReview = visibiliteReview;
        this.estValide = estValide;
        this.numeroSiret = numeroSiret;
        this.evenementsLiked = evenementsLiked;
        this.reviews = reviews;
        this.evenementsCrees = evenementsCrees;
    }

    public Compte(Integer id, String email, String login, String passwordHash, String nom, String prenom, String adresse, Date dob, String photo, String description, String role, Boolean visibiliteReview, Boolean estValide, String numeroSiret, Set<Evenement> evenementsLiked, Set<Review> reviews, Set<Evenement> evenementsCrees) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.passwordHash = passwordHash;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.dob = dob;
        this.photo = photo;
        this.description = description;
        this.role = role;
        this.visibiliteReview = visibiliteReview;
        this.estValide = estValide;
        this.numeroSiret = numeroSiret;
        this.evenementsLiked = evenementsLiked;
        this.reviews = reviews;
        this.evenementsCrees = evenementsCrees;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getVisibiliteReview() {
        return visibiliteReview;
    }

    public void setVisibiliteReview(Boolean visibiliteReview) {
        this.visibiliteReview = visibiliteReview;
    }

    public Boolean getEstValide() {
        return estValide;
    }

    public void setEstValide(Boolean estValide) {
        this.estValide = estValide;
    }

    public String getNumeroSiret() {
        return numeroSiret;
    }

    public void setNumeroSiret(String numeroSiret) {
        this.numeroSiret = numeroSiret;
    }


    // ---------------- RELATIONSHIP GETTERS SETTERS ----------------
    public Set<Evenement> getEvenementsLiked() {
        return evenementsLiked;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public Set<Evenement> getEvenementsCrees() {
        return evenementsCrees;
    }

    public void likeEvenement(Evenement evenement) {
        evenementsLiked.add(evenement);
    }

    // ------------------------- TO STRING --------------------------
    @Override
    public String toString() {
        return "Compte{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", dob=" + dob +
                ", photo='" + photo + '\'' +
                ", description='" + description + '\'' +
                ", role='" + role + '\'' +
                ", visibiliteReview=" + visibiliteReview +
                ", estValide=" + estValide +
                ", numeroSiret='" + numeroSiret + '\'' +
                '}';
    }
}
