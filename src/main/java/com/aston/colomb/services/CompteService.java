package com.aston.colomb.services;

import com.aston.colomb.dao.CompteRepository;
import com.aston.colomb.entities.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompteService {

    private final CompteRepository compteRepository;

    @Autowired
    public CompteService(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
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
}
