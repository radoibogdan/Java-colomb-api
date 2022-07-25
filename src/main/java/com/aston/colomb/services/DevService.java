package com.aston.colomb.services;

import com.aston.colomb.dao.CompteRepository;
import com.aston.colomb.dao.EvenementRepository;
import com.aston.colomb.dao.ReviewRepository;
import com.aston.colomb.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
public class DevService {

    private final CompteRepository compteRepository;
    private final ReviewRepository reviewRepository;
    private final EvenementRepository evenementRepository;

    @Autowired
    public DevService(CompteRepository compteRepository, ReviewRepository reviewRepository, EvenementRepository evenementRepository) {
        this.compteRepository = compteRepository;
        this.reviewRepository = reviewRepository;
        this.evenementRepository = evenementRepository;
    }
    public void initializeDatabase() {
        compteRepository.saveAll(Arrays.asList(
              new Compte(
                      1,
                    "bogdan@gmail.com",
                    "bogdan2022",
                    "P@ssw0rd!",
                    "Radoi",
                    "Bogdan",
                    "ROLE_ADMIN"),
              new Compte(
                      2,
                      "jean@gmail.com",
                      "jean-le-grand",
                      "P@ssw0rd!",
                      "Jean",
                      "DuPont",
                      "ROLE_USER"),
              new Compte(
                      3,
                      "daria@gmail.com",
                      "daria2",
                      "P@ssw0rd!",
                      "Rychkova",
                      "Daria",
                      "ROLE_ENTREPRISE")
        )); // .forEach(System.out::println)



        evenementRepository.saveAll(Arrays.asList(
                new Evenement(
                        1,
                        "Evenement Comedie",
                        LocalDateTime.of(2022, Month.MAY, 20, 00, 30, 0),
                        LocalDateTime.of(2022, Month.MAY, 20, 9, 30, 0),
                        LocalDateTime.of(2022, Month.MAY, 20, 18, 30, 0),
                        40F,
                        "40 rue de Evenement",
                        "musée",
                        "https://www.tours-evenements.com/sites/default/files/media/header_image/header-tours-evenements.jpg",
                        "Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis",
                        50,
                        2,
                        40,
                        false,
                        true,
                        40.22,
                        22.22),
                new Evenement(
                        2,
                        "Stand-up Pierre Thevenoux",
                        LocalDateTime.of(2022, Month.MAY, 20, 00, 30, 0),
                        LocalDateTime.of(2022, Month.MAY, 20, 9, 30, 0),
                        LocalDateTime.of(2022, Month.MAY, 20, 18, 30, 0),
                        40F,
                        "40 rue de Evenement",
                        "théâtre",
                        "https://www.tours-evenements.com/sites/default/files/media/header_image/header-tours-evenements.jpg",
                        "Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis",
                        50,
                        2,
                        40,
                        false,
                        false,
                        40.22,
                        22.22),
                new Evenement(
                        3,
                        "Festival de Jazz",
                        LocalDateTime.of(2022, Month.MAY, 20, 00, 30, 0),
                        LocalDateTime.of(2022, Month.MAY, 20, 9, 30, 0),
                        LocalDateTime.of(2022, Month.MAY, 20, 18, 30, 0),
                        40F,
                        "40 rue de Evenement",
                        "musique",
                        "https://www.tours-evenements.com/sites/default/files/media/header_image/header-tours-evenements.jpg",
                        "Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis",
                        50,
                        2,
                        40,
                        false,
                        false,
                        40.22,
                        22.22)
        )).forEach(evenement -> {
            // compte id 3 = compte avec ROLE_ENTREPRISE
            Compte compteEntreprise = compteRepository.findById(3).get();
            evenement.setCompte(compteEntreprise);
            evenementRepository.save(evenement);
        });

        reviewRepository.saveAll(Arrays.asList(
                new Review(
                        1,
                        LocalDateTime.now(),
                        4,
                        22,
                        "Sympa mais pas plus, je veux veux un remboursement",
                        false),
                new Review(
                        2,
                        LocalDateTime.now(),
                        5,
                        2,
                        "Ingenieux et spectaculaire",
                        true),
                new Review(
                        3,
                        LocalDateTime.now(),
                        4,
                        1,
                        "Bref, je reviendrai pas",
                        false),
                new Review(
                        4,
                        LocalDateTime.now(),
                        1,
                        0,
                        "Sympa mais pas plus, je veux veux un remboursement",
                        false),
                new Review(
                        5,
                        LocalDateTime.now(),
                        4,
                        200,
                        "Moyen mais pas cher, je ne recommande pas",
                        false)
        )).forEach(review -> {
            int randCompte = ThreadLocalRandom.current().nextInt(1, 3 + 1);
            Compte compteAleatoire = compteRepository.findById(randCompte).get();
            review.setCompte(compteAleatoire);

            int randEvenement = ThreadLocalRandom.current().nextInt(1, 3 + 1);
            Evenement evenementAleatoire = evenementRepository.findById(randEvenement).get();
            review.setEvenement(evenementAleatoire);

            reviewRepository.save(review);
        });

        compteRepository.findAll().forEach(compte -> {
            int randEvenement = ThreadLocalRandom.current().nextInt(1, 3 + 1);
            Evenement evenementAleatoire = evenementRepository.findById(randEvenement).get();
            compte.likeEvenement(evenementAleatoire);
            compteRepository.save(compte);
        });

//        compteRepository.saveAll(
//            Arrays.asList(
//                    fillInLatLng(new Compte("Boston, MA")),
//                    fillInLatLng(new Compte("Framingham, MA")),
//                    fillInLatLng(new Compte("Waltham, MA")))
//        ).forEach(System.out::println);
    }
}


//        new Compte
//        {
//        Id = "1",
//        Email = "bogdan@gmail.com",
//        UserName = "bogdan",
//        PasswordHash = "bogdan",
//        Nom = "Radoi",
//        Prenom = "Bogdan",
//        Adresse = "40 rue de Compte",
//        DOB = new DateTime(1988, 12, 10, 0, 0, 0),
//        Photo = "chemin/photo_compte_profil",
//        Description = "Description",
//        /*Role = "ROLE_USER",*/
//        VisibiliteReviews = true,
//        estValide = true,
//        NumeroSiret = null
//        },
//        new Compte
//        {
//        Id = "2",
//        Email = "wally@gmail.com",
//        UserName = "wally",
//        PasswordHash = "wally",
//        Nom = "Cisse",
//        Prenom = "Wally",
//        Adresse = "40 rue de Paris",
//        DOB = new DateTime(1992, 6, 12, 0, 0, 0),
//        Photo = "dossier/photo",
//        Description = "Description",
//        /*Role = "ROLE_USER",*/
//        VisibiliteReviews = true,
//        estValide = true,
//        NumeroSiret = null
//        },
//        new Compte
//        {
//        Id = "3",
//        Email = "daria@gmail.com",
//        UserName = "daria",
//        PasswordHash = "Daria",
//        Nom = "Rychkova",
//        Prenom = "daria",
//        Adresse = "40 rue de Paris",
//        DOB = new DateTime(1990, 5, 20, 0, 0, 0),
//        Photo = "dossier/photo",
//        Description = "Description",
//        /*Role = "ROLE_USER",*/
//        VisibiliteReviews = true,
//        estValide = true,
//        NumeroSiret = null
//        }
//        );
//
//
//
//
//        new Evenement
//        {
//        EvenementId = 1,
//        Nom = "Evenement Comedie",
//        Date = new DateTime(2022, 5, 20, 0, 0, 0),
//        HeureOuverture = new DateTime(2022, 5, 20, 9, 0, 0),
//        HeureFermeture = new DateTime(2022, 5, 20, 18, 0, 0),
//        Prix = 40,
//        Adresse = "40 rue de Evenement",
//        Categorie = "musee",
//        Photo = "https://www.tours-evenements.com/sites/default/files/media/header_image/header-tours-evenements.jpg",
//        Description = "Description evenement",
//        NombrePersMax = 50,
//        NombreLikes = 2,
//        NombreVues = 40,
//        EstSignale = false,
//        EstSuspendu = false,
//        Longitude = 40.22,
//        Latitude = 22.22,
//        /*CompteId = 1*/
//        },
//        new Evenement
//        {
//        EvenementId = 2,
//        Nom = "Evenement dehors",
//        Date = new DateTime(2022, 6, 15, 0, 0, 0),
//        HeureOuverture = new DateTime(2022, 6, 15, 9, 0, 0),
//        HeureFermeture = new DateTime(2022, 6, 15, 19, 0, 0),
//        Prix = 300,
//        Adresse = "40 rue de Evenement",
//        Categorie = "theatre",
//        Photo = "https://www.tours-evenements.com/sites/default/files/media/header_image/header-tours-evenements.jpg",
//        Description = "Description evenement",
//        NombrePersMax = 20,
//        NombreLikes = 440,
//        NombreVues = 140,
//        EstSignale = false,
//        EstSuspendu = false,
//        Longitude = 55.22,
//        Latitude = 11.22,
//        /*CompteId = 2*/
//        },
//        new Evenement
//        {
//        EvenementId = 3,
//        Nom = "Evenement Parc",
//        Date = new DateTime(2022, 2, 10, 0, 0, 0),
//        HeureOuverture = new DateTime(2022, 2, 10, 10, 0, 0),
//        HeureFermeture = new DateTime(2022, 2, 10, 17, 0, 0),
//        Prix = 146,
//        Adresse = "40 rue de Evenement",
//        Categorie = "spectacle",
//        Photo = "https://www.tours-evenements.com/sites/default/files/media/header_image/header-tours-evenements.jpg",
//        Description = "Description evenement",
//        NombrePersMax = 250,
//        NombreLikes = 112,
//        NombreVues = 140,
//        EstSignale = true,
//        EstSuspendu = true,
//        Longitude = 140.22,
//        Latitude = 122.22,
//        /*CompteId = 1*/
//        }
//
//
//        new Review
//        {
//        ReviewId = 1,
//        Date = new DateTime(2022, 12, 31, 17, 0, 0),
//        NombreEtoiles = 2,
//        NombreLikes = 2,
//        Contenu = "Sympa mais pas plus, je veux veux un remboursement",
//        EstSignale = true,
//        /*CompteId = 2,*/
//        EvenementId = 3,
//        },
//        new Review
//        {
//        ReviewId = 2,
//        Date = new DateTime(2022, 3, 14, 17, 0, 0),
//        NombreEtoiles = 5,
//        NombreLikes = 1,
//        Contenu = "Tres bien",
//        EstSignale = false,
//        /*CompteId = 3,*/
//        EvenementId = 2,
//        },
//        new Review
//        {
//        ReviewId = 3,
//        Date = new DateTime(2022, 2, 15, 17, 0, 0),
//        NombreEtoiles = 1,
//        NombreLikes = 0,
//        Contenu = "Bref, je reviendrai pas",
//        EstSignale = false,
//        /*CompteId = 1,*/
//        EvenementId = 1,
//        },
//        new Review
//        {
//        ReviewId = 4,
//        Date = new DateTime(2022, 5, 17, 17, 0, 0),
//        NombreEtoiles = 3,
//        NombreLikes = 11,
//        Contenu = "Ingenieux et spectaculaire",
//        EstSignale = false,
//        /*CompteId = 2,*/
//        EvenementId = 3,
//        },
//        new Review
//        {
//        ReviewId = 5,
//        Date = new DateTime(2022, 9, 19, 17, 0, 0),
//        NombreEtoiles = 2,
//        NombreLikes = 22,
//        Contenu = "Moyen mais pas cher, je ne recommande pas",
//        EstSignale = false,
//        /*CompteId = 1,*/
//        EvenementId = 3,
//        }