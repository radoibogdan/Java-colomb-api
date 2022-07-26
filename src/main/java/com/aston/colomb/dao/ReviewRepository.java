package com.aston.colomb.dao;

import com.aston.colomb.entities.Evenement;
import com.aston.colomb.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    // Find all Reviews with Id Compte provided
    public List<Review> findByCompte_Id(Integer id);

    // Find all Reviews that are reported (isSignalé = true)
    List<Review> findByEstSignale(Boolean estSignale);
}
