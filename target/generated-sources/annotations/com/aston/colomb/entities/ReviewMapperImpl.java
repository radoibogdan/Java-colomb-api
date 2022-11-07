package com.aston.colomb.entities;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-07T14:36:07+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.4 (Oracle Corporation)"
)
@Component
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public void updateReview(Review reviewFromApi, Review reviewFromBdd) {
        if ( reviewFromApi == null ) {
            return;
        }

        if ( reviewFromApi.getId() != null ) {
            reviewFromBdd.setId( reviewFromApi.getId() );
        }
        if ( reviewFromApi.getDate() != null ) {
            reviewFromBdd.setDate( reviewFromApi.getDate() );
        }
        if ( reviewFromApi.getNombreEtoiles() != null ) {
            reviewFromBdd.setNombreEtoiles( reviewFromApi.getNombreEtoiles() );
        }
        if ( reviewFromApi.getNombreLikes() != null ) {
            reviewFromBdd.setNombreLikes( reviewFromApi.getNombreLikes() );
        }
        if ( reviewFromApi.getContenu() != null ) {
            reviewFromBdd.setContenu( reviewFromApi.getContenu() );
        }
        if ( reviewFromApi.getEstSignale() != null ) {
            reviewFromBdd.setEstSignale( reviewFromApi.getEstSignale() );
        }
        if ( reviewFromApi.getCompte() != null ) {
            reviewFromBdd.setCompte( reviewFromApi.getCompte() );
        }
        if ( reviewFromApi.getEvenement() != null ) {
            reviewFromBdd.setEvenement( reviewFromApi.getEvenement() );
        }
    }
}
