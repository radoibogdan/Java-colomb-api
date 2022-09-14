package com.aston.colomb.services;

import com.aston.colomb.dao.CompteRepository;
import com.aston.colomb.dao.EvenementRepository;
import com.aston.colomb.entities.Compte;
import com.aston.colomb.entities.Evenement;
import com.aston.colomb.entities.EvenementMapper;
import com.aston.colomb.exception.EvenementNotFoundException;
import com.aston.colomb.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    public List<Evenement> getAllEvenements() {
        return evenementRepository.findAll();
    }

    public Evenement saveEvenement(Evenement evenement) {
        return evenementRepository.save(evenement);
    }

    public Optional<Evenement> findEvenementById(Integer id) {
        Optional<Evenement> evenementOptional = evenementRepository.findById(id);
        if (!evenementOptional.isPresent()) {
            throw new EvenementNotFoundException("L'événement que vous voulez accéder n'existe pas.");
        }
        return evenementOptional;
    }

    public void addCompteToEvenement (Integer entrepriseId, Evenement evenement) {
        Optional<Compte> compteOptional = compteRepository.findById(entrepriseId);
        if (!compteOptional.isPresent()) {
            throw new UserNotFoundException("L'entreprise que vous voulez rajouter à cet événement n'existe pas.");
        }
        Compte compteBdd = compteOptional.get();
        evenement.setCompte(compteBdd);
        evenementRepository.save(evenement);
    }

    public List<Evenement> getAllEvenementsEntreprise(Integer id) {
        return evenementRepository.findByCompte_Id(id);
    }

    public List<Evenement> getAllEvenementsReported() {
        return evenementRepository.findByEstSignale(true);
    }

    public void updateEvenementEstSuspendu(Integer id, Evenement evenement) {
        Evenement evenementBdd = evenementRepository.findById(id).get();
        if (evenementBdd == null) {
            throw new EvenementNotFoundException("L'événement que vous voulez modifier n'existe pas.");
        }
        evenementBdd.setEstSuspendu(evenement.getEstSuspendu());
        evenementRepository.save(evenementBdd);
    }

    public void deleteEvenement(Integer id) {
        Optional<Evenement> evenementOptional = evenementRepository.findById(id);
        if (!evenementOptional.isPresent()) {
            throw new EvenementNotFoundException("L'événement que vous voulez supprimer n'existe pas.");
        }
        evenementRepository.delete(evenementOptional.get());
    }

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
