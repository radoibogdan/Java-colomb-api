package com.aston.colomb.dao;

import com.aston.colomb.entities.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvenementRepository extends JpaRepository<Evenement, Integer> {

    // Find all Evenements with Id Compte provided
    public List<Evenement> findByCompte_Id(Integer id);

    // Find all Evenements that are reported (isSignalé = true)
    List<Evenement> findByEstSignale(Boolean estSignale);

    // Find Evenement by Id de l'Api de Paris
    Optional<Evenement> findByIdApiParis(Integer idApiParis);
}
