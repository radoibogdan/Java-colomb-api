package com.aston.colomb.entities;

import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-06T19:24:21+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.4 (Oracle Corporation)"
)
@Component
public class EvenementMapperImpl implements EvenementMapper {

    @Override
    public void updateEvenement(Evenement evenementFromApi, Evenement evenementFromBdd) {
        if ( evenementFromApi == null ) {
            return;
        }

        if ( evenementFromApi.getId() != null ) {
            evenementFromBdd.setId( evenementFromApi.getId() );
        }
        if ( evenementFromApi.getNom() != null ) {
            evenementFromBdd.setNom( evenementFromApi.getNom() );
        }
        if ( evenementFromApi.getDate() != null ) {
            evenementFromBdd.setDate( evenementFromApi.getDate() );
        }
        if ( evenementFromApi.getHeureOuverture() != null ) {
            evenementFromBdd.setHeureOuverture( evenementFromApi.getHeureOuverture() );
        }
        if ( evenementFromApi.getHeureFermeture() != null ) {
            evenementFromBdd.setHeureFermeture( evenementFromApi.getHeureFermeture() );
        }
        if ( evenementFromApi.getPrix() != null ) {
            evenementFromBdd.setPrix( evenementFromApi.getPrix() );
        }
        if ( evenementFromApi.getAdresse() != null ) {
            evenementFromBdd.setAdresse( evenementFromApi.getAdresse() );
        }
        if ( evenementFromApi.getCategorie() != null ) {
            evenementFromBdd.setCategorie( evenementFromApi.getCategorie() );
        }
        if ( evenementFromApi.getPhoto() != null ) {
            evenementFromBdd.setPhoto( evenementFromApi.getPhoto() );
        }
        if ( evenementFromApi.getDescription() != null ) {
            evenementFromBdd.setDescription( evenementFromApi.getDescription() );
        }
        if ( evenementFromApi.getNombrePersMax() != null ) {
            evenementFromBdd.setNombrePersMax( evenementFromApi.getNombrePersMax() );
        }
        if ( evenementFromApi.getNombreLikes() != null ) {
            evenementFromBdd.setNombreLikes( evenementFromApi.getNombreLikes() );
        }
        if ( evenementFromApi.getNombreVues() != null ) {
            evenementFromBdd.setNombreVues( evenementFromApi.getNombreVues() );
        }
        if ( evenementFromApi.getEstSignale() != null ) {
            evenementFromBdd.setEstSignale( evenementFromApi.getEstSignale() );
        }
        if ( evenementFromApi.getEstSuspendu() != null ) {
            evenementFromBdd.setEstSuspendu( evenementFromApi.getEstSuspendu() );
        }
        evenementFromBdd.setLongitude( evenementFromApi.getLongitude() );
        evenementFromBdd.setLatitude( evenementFromApi.getLatitude() );
        if ( evenementFromApi.getIdApiParis() != null ) {
            evenementFromBdd.setIdApiParis( evenementFromApi.getIdApiParis() );
        }
        if ( evenementFromApi.getCompte() != null ) {
            evenementFromBdd.setCompte( evenementFromApi.getCompte() );
        }
        if ( evenementFromBdd.getReviews() != null ) {
            evenementFromBdd.getReviews().clear();
            Set<Review> set = evenementFromApi.getReviews();
            if ( set != null ) {
                evenementFromBdd.getReviews().addAll( set );
            }
        }
        if ( evenementFromBdd.getLikedByComptes() != null ) {
            evenementFromBdd.getLikedByComptes().clear();
            Set<Compte> set1 = evenementFromApi.getLikedByComptes();
            if ( set1 != null ) {
                evenementFromBdd.getLikedByComptes().addAll( set1 );
            }
        }
    }
}
