package com.aston.colomb.services;

import com.aston.colomb.dao.EvenementRepository;
import com.aston.colomb.entities.Evenement;
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
        return evenementRepository.findById(id);
    }

    public List<Evenement> getAllEvenementsEntreprise(Integer id) {
        return evenementRepository.findByCompte_Id(id);
    }

    public List<Evenement> getAllEvenementsReported() {
        return evenementRepository.findByEstSignale(true);
    }

    public void updateEvenementEstSuspendu(Integer id, Evenement evenement) {
        Evenement evenementBdd = evenementRepository.findById(id).get();
        boolean estSuspendu = evenement.getEstSuspendu();
        System.out.println(estSuspendu);
        evenementBdd.setEstSuspendu(estSuspendu);
        evenementRepository.save(evenementBdd);
    }

    public void deleteEvenement(Integer id) {
        evenementRepository.deleteById(id);
    }
}
