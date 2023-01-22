package com.aston.colomb.entities;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-22T19:14:51+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.4 (Oracle Corporation)"
)
@Component
public class CompteMapperImpl implements CompteMapper {

    @Override
    public void updateCompte(Compte compteFromApi, Compte compteFromBdd) {
        if ( compteFromApi == null ) {
            return;
        }

        if ( compteFromApi.getId() != null ) {
            compteFromBdd.setId( compteFromApi.getId() );
        }
        if ( compteFromApi.getEmail() != null ) {
            compteFromBdd.setEmail( compteFromApi.getEmail() );
        }
        if ( compteFromApi.getUsername() != null ) {
            compteFromBdd.setUsername( compteFromApi.getUsername() );
        }
        if ( compteFromApi.getPasswordHash() != null ) {
            compteFromBdd.setPasswordHash( compteFromApi.getPasswordHash() );
        }
        if ( compteFromApi.getNom() != null ) {
            compteFromBdd.setNom( compteFromApi.getNom() );
        }
        if ( compteFromApi.getPrenom() != null ) {
            compteFromBdd.setPrenom( compteFromApi.getPrenom() );
        }
        if ( compteFromApi.getAdresse() != null ) {
            compteFromBdd.setAdresse( compteFromApi.getAdresse() );
        }
        if ( compteFromApi.getDob() != null ) {
            compteFromBdd.setDob( compteFromApi.getDob() );
        }
        if ( compteFromApi.getPhoto() != null ) {
            compteFromBdd.setPhoto( compteFromApi.getPhoto() );
        }
        if ( compteFromApi.getDescription() != null ) {
            compteFromBdd.setDescription( compteFromApi.getDescription() );
        }
        if ( compteFromBdd.getRoles() != null ) {
            Set<Role> set = compteFromApi.getRoles();
            if ( set != null ) {
                compteFromBdd.getRoles().clear();
                compteFromBdd.getRoles().addAll( set );
            }
        }
        else {
            Set<Role> set = compteFromApi.getRoles();
            if ( set != null ) {
                compteFromBdd.setRoles( new HashSet<Role>( set ) );
            }
        }
        if ( compteFromApi.getVisibiliteReview() != null ) {
            compteFromBdd.setVisibiliteReview( compteFromApi.getVisibiliteReview() );
        }
        if ( compteFromApi.getEstValide() != null ) {
            compteFromBdd.setEstValide( compteFromApi.getEstValide() );
        }
        if ( compteFromApi.getNumeroSiret() != null ) {
            compteFromBdd.setNumeroSiret( compteFromApi.getNumeroSiret() );
        }
        if ( compteFromBdd.getEvenementsLiked() != null ) {
            compteFromBdd.getEvenementsLiked().clear();
            Set<Evenement> set1 = compteFromApi.getEvenementsLiked();
            if ( set1 != null ) {
                compteFromBdd.getEvenementsLiked().addAll( set1 );
            }
        }
        if ( compteFromBdd.getReviews() != null ) {
            compteFromBdd.getReviews().clear();
            Set<Review> set2 = compteFromApi.getReviews();
            if ( set2 != null ) {
                compteFromBdd.getReviews().addAll( set2 );
            }
        }
        if ( compteFromBdd.getEvenementsCrees() != null ) {
            compteFromBdd.getEvenementsCrees().clear();
            Set<Evenement> set3 = compteFromApi.getEvenementsCrees();
            if ( set3 != null ) {
                compteFromBdd.getEvenementsCrees().addAll( set3 );
            }
        }
    }
}
