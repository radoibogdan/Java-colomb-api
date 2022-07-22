package com.aston.colomb.services;

import com.aston.colomb.dao.CompteRepository;
import com.aston.colomb.entities.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompteService {

    private final CompteRepository siteRepository;

    @Autowired
    public CompteService(CompteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

//    public void initializeDatabase() {
//        siteRepository.saveAll(
//                Arrays.asList(
//                        fillInLatLng(new Compte("Boston, MA")),
//                        fillInLatLng(new Compte("Framingham, MA")),
//                        fillInLatLng(new Compte("Waltham, MA")))
//        ).forEach(System.out::println);
//    }

    public List<Compte> getAllComptes() {
        return siteRepository.findAll();
    }

    public Compte saveCompte(Compte compte) {
        return siteRepository.save(compte);
    }

    public Optional<Compte> findCompteById(Integer id) {
        return siteRepository.findById(id);
    }
}
