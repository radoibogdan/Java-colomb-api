package com.aston.colomb.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aston.colomb.entities.Compte;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Integer> {

    Optional<Compte> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query(
        value = """
                SELECT 
                    c.nom, 
                    c.prenom, 
                    r.contenu 
                FROM compte c 
                    JOIN review r ON c.id = r.compte_id 
                WHERE 
                    c.id = ?1
        """,
        nativeQuery = true)
    List<Object[]> findUserAndAllHisReviewsSql(int idCompte);

}
