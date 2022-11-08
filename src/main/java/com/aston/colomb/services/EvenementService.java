package com.aston.colomb.services;

import com.aston.colomb.dao.CompteRepository;
import com.aston.colomb.dao.EvenementRepository;
import com.aston.colomb.entities.Compte;
import com.aston.colomb.entities.Evenement;
import com.aston.colomb.exception.EvenementNotFoundException;
import com.aston.colomb.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 *  Service (CRUD) qui intéragit avec la table Evenement de la base de données
 */
@Service
@Transactional
public class EvenementService {

    private final EvenementRepository evenementRepository;
    private final CompteRepository compteRepository;
    private final EvenementMapper evenementMapper;

    @Autowired
    public EvenementService(EvenementRepository evenementRepository, EvenementMapper evenementMapper, CompteRepository compteRepository) {
        this.evenementRepository = evenementRepository;
        this.compteRepository = compteRepository;
        this.evenementMapper = evenementMapper;
    }

    /**
     * Récupère tous les événements depuis la bdd
     *
     * @return Liste d'événements
     */
    public List<Evenement> getAllEvenements() {
        return evenementRepository.findAll();
    }

    /**
     * Créé un nouveau événement dans la bdd
     *
     * @param evenement  Evénement à créer
     * @return           Renvoie l'élément créé
     */
    public Evenement saveEvenement(Evenement evenement) {
        return evenementRepository.save(evenement);
    }

    /**
     * Trouve un événement en utilisant son id
     *
     * @param id Id événement
     * @return   Evénement trouvé
     */
    public Optional<Evenement> findEvenementById(Integer id) {
        Optional<Evenement> evenementOptional = evenementRepository.findById(id);
        if (!evenementOptional.isPresent()) {
            throw new EvenementNotFoundException("L'événement que vous voulez accéder n'existe pas.");
        }
        return evenementOptional;
    }
    /**
     * Trouve un événement en utilisant son id de l'api de Paris
     *
     * @param idApiDeParis Id Api de Paris de l'événement
     * @return             Evénement trouvé
     */
    public Optional<Evenement> findEvenementByIdApiDeParis(Integer idApiDeParis) {
        Optional<Evenement> evenementOptional = evenementRepository.findByIdApiParis(idApiDeParis);
        if (!evenementOptional.isPresent()) {
            throw new EvenementNotFoundException("L'événement que vous voulez récupérer n'existe pas.");
        }
        return evenementOptional;
    }

    /**
     * Rattache un compte entreprise à un événement existant
     *
     * @param entrepriseId Id compte entreprise
     * @param evenement    Evénement (entité)
     */
    public void addCompteToEvenement (Integer entrepriseId, Evenement evenement) {
        Optional<Compte> compteOptional = compteRepository.findById(entrepriseId);
        if (!compteOptional.isPresent()) {
            throw new UserNotFoundException("L'entreprise que vous voulez rajouter à cet événement n'existe pas.");
        }
        Compte compteBdd = compteOptional.get();
        evenement.setCompte(compteBdd);
        evenementRepository.save(evenement);
    }

    /**
     * Récupère tous les événements créés par une entreprise
     *
     * @param id Id compte entreprise
     * @return   Liste événéments trouvés
     */
    public List<Evenement> getAllEvenementsEntreprise(Integer id) {
        return evenementRepository.findByCompte_Id(id);
    }

    /**
     * Récupère tous les événements signalés
     *
     * @return Liste événements signalés
     */
    public List<Evenement> getAllEvenementsReported() {
        return evenementRepository.findByEstSignale(true);
    }

    /**
     * Met à jour la propriété "estSuspendu" de l'événement
     *
     * @param id        Id événéments à mettre à jour
     * @param evenement Evénements (entité)
     */
    public void updateEvenementEstSuspendu(Integer id, Evenement evenement) {
        Evenement evenementBdd = evenementRepository.findById(id).get();
        if (evenementBdd == null) {
            throw new EvenementNotFoundException("L'événement que vous voulez modifier n'existe pas.");
        }
        evenementBdd.setEstSuspendu(evenement.getEstSuspendu());
        evenementRepository.save(evenementBdd);
    }

    /**
     * Supprime un événement
     *
     * @param id Id événements à supprimer
     */
    public void deleteEvenement(Integer id) {
        Optional<Evenement> evenementOptional = evenementRepository.findById(id);
        if (!evenementOptional.isPresent()) {
            throw new EvenementNotFoundException("L'événement que vous voulez supprimer n'existe pas.");
        }
        evenementRepository.delete(evenementOptional.get());
    }

    /**
     * Modifie une ou plusieurs propriétés d'un événement
     *
     * @param id        Id événements à modifier
     * @param evenement Evénement (entité) à modifier
     * @return          Evenement modifié
     */
    public Evenement editEvenement(Integer id, Evenement evenement) {
        Optional<Evenement> evenementOptional = evenementRepository.findById(id);
        if (!evenementOptional.isPresent()) {
            throw new EvenementNotFoundException("L'événement que vous voulez modifier n'existe pas.");
        }
        Evenement evenementBdd = evenementOptional.get();
        evenementMapper.updateEvenement(evenement, evenementBdd);
        evenementRepository.save(evenementBdd);
        return evenementBdd;
    }
}
