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

/**
 * Service qui s'occupe de la création d'une fausse base de données avec quelques lignes pour chaque table
 * Utile en mode développement
  */
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

    /**
     * Initialise la base de données avec des fausses informations
     */
    public void initializeDatabase() {
        /* Comptes */
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

        /* Evénements */
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
                        40.22,
                        777777),
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
                        22.22,
                        888888),
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
                        22.22,
                        999999)
        )).forEach(evenement -> {
            // compte id 3 = compte avec ROLE_ENTREPRISE
            Compte compteEntreprise = compteRepository.findById(3).get();
            evenement.setCompte(compteEntreprise);
            evenementRepository.save(evenement);
        });

        /* Avis */
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
        )).forEach(review -> { // rattache à l'avis un Compte et un Evénement aléatoire
            int randCompte = ThreadLocalRandom.current().nextInt(1, 3 + 1);
            Compte compteAleatoire = compteRepository.findById(randCompte).get();
            review.setCompte(compteAleatoire);

            int randEvenement = ThreadLocalRandom.current().nextInt(1, 3 + 1);
            Evenement evenementAleatoire = evenementRepository.findById(randEvenement).get();
            review.setEvenement(evenementAleatoire);

            reviewRepository.save(review);
        });

        // Pour chaque Compte rattache un like à un Evénement aléatoire
        compteRepository.findAll().forEach(compte -> {
            int randEvenement = ThreadLocalRandom.current().nextInt(1, 3 + 1);
            Evenement evenementAleatoire = evenementRepository.findById(randEvenement).get();
            compte.likeEvenement(evenementAleatoire);
            compteRepository.save(compte);
        });
    }
}