package com.aston.colomb.services;

import com.aston.colomb.dao.CompteRepository;
import com.aston.colomb.entities.Compte;
import com.aston.colomb.entities.CompteMapper;
import com.aston.colomb.exception.CompteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompteService {

    private final CompteRepository compteRepository;

    private final CompteMapper compteMapper;

    @Autowired
    public CompteService(CompteRepository compteRepository, CompteMapper compteMapper) {
        this.compteRepository = compteRepository;
        this.compteMapper = compteMapper;
    }

    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    public Compte saveCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    public Optional<Compte> findCompteById(Integer id) {
        return compteRepository.findById(id);
    }

    /**
     * Supprime un compte
     *
     * @param id Id compte à supprimer
     */
    public void deleteCompte(Integer id) {
        Optional<Compte> compteOptional = compteRepository.findById(id);
        if (!compteOptional.isPresent()) {
            throw new CompteNotFoundException("Le compte que vous voulez supprimer n'existe pas.");
        }
        compteRepository.delete(compteOptional.get());
    }

    /**
     * Modifie une ou plusieurs propriétés d'un compte
     *
     * @param id        Id compte à modifier
     * @param compte Compte (entité) à modifier
     * @return          Compte modifié
     */
    public Compte editCompte(Integer id, Compte compte) {
        Optional<Compte> compteOptional = compteRepository.findById(id);
        if (!compteOptional.isPresent()) {
            throw new CompteNotFoundException("Le compte que vous voulez modifier n'existe pas.");
        }
        Compte compteBdd = compteOptional.get();
        compteMapper.updateCompte(compte, compteBdd);
        compteRepository.save(compteBdd);
        return compteBdd;
    }
}
