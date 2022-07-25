package com.aston.colomb.services;

import com.aston.colomb.dao.EvenementRepository;
import com.aston.colomb.entities.Evenement;
import com.aston.colomb.exception.EvenementNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EvenementService {

    private final EvenementRepository evenementRepository;

    @Autowired
    public EvenementService(EvenementRepository evenementRepository) {
        this.evenementRepository = evenementRepository;
    }

    public List<Evenement> getAllEvenements() {
        return evenementRepository.findAll();
    }

    public Evenement saveEvenement(Evenement compte) {
        return evenementRepository.save(compte);
    }

    public Optional<Evenement> findEvenementById(Integer id) {
        Optional<Evenement> evenementOptional = evenementRepository.findById(id);
        if (!evenementOptional.isPresent()) {
            throw new EvenementNotFoundException("L'événement que vous voulez accéder n'existe pas.");
        }
        return evenementOptional;
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

    public Optional<Evenement> editEvenement(Integer id, Evenement evenement) {
        Evenement evenementBdd = evenementRepository.findById(id).get();

    }
}
