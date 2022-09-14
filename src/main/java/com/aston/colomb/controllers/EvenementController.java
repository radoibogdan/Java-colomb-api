package com.aston.colomb.controllers;

import com.aston.colomb.entities.Evenement;
import com.aston.colomb.services.EvenementService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/evenements")
public class EvenementController {
    private final EvenementService evenementService;

    // Auto wiring of service in constructor
    public EvenementController(EvenementService evenementService) {
        this.evenementService = evenementService;
    }

    /* ------------------ GET ALL ------------------ */
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Tous les événements ont été récupérés.",
                    content = {
                            @Content( mediaType = "application/json", schema = @Schema(implementation = Evenement.class))
                    })
    })
    public List<Evenement> findAll() {
        return evenementService.getAllEvenements();
    }

    /* ------------------ GET All by Id Entreprise ------------------ */
    @GetMapping("/entreprise/{id}")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Tous les événements entreprise ont été récupérés.",
                    content = {
                            @Content( mediaType = "application/json", schema = @Schema(implementation = Evenement.class))
                    }),
            @ApiResponse(responseCode = "400", description = "L'id entreprise passé dans l'url doit être un entier.", content = @Content)
    })
    public List<Evenement> findAllByEntrepriseId(@PathVariable Integer id) {
        return evenementService.getAllEvenementsEntreprise(id);
    }

    /* ------------------ GET All Evenements Reported ------------------ */
    @GetMapping("/reported")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Tous les événements signalés ont été récupérés.",
                    content = {
                            @Content( mediaType = "application/json", schema = @Schema(implementation = Evenement.class))
                    })
    })
    public List<Evenement> findAllReported() {
        return evenementService.getAllEvenementsReported();
    }

    /* ------------------ GET by Id ------------------ */
    // Le ResponseEntity.of renvoie un Body vide + 404 dans le cas d'un Optional vide, OR Body avec le Evenement + 200
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Evénement trouvé",
                    content = {
                            @Content( mediaType = "application/json", schema = @Schema(implementation = Evenement.class))
                    }),
            @ApiResponse(responseCode = "400", description = "L'id passé dans l'url doit être un entier.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Evénement non trouvé", content = @Content)
    })
    public ResponseEntity<Evenement> findById(@PathVariable Integer id) {
        return ResponseEntity.of(evenementService.findEvenementById(id));
    }

    /* ------------------ POST CREATE ------------------ */
    @PostMapping("/{entrepriseId}")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "201", description = "L'événement a été créé.",
                    content = {
                            @Content( mediaType = "application/json", schema = @Schema(implementation = Evenement.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Les informations envoyés sont incorrectes.", content = @Content),
    })
    public ResponseEntity<Evenement> saveEvenement(@PathVariable Integer entrepriseId, @Valid @RequestBody Evenement evenement) {
        Evenement evenementCree = evenementService.saveEvenement(evenement);
        evenementService.addCompteToEvenement(entrepriseId, evenementCree);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(evenementCree.getId())
                .toUri();
        return ResponseEntity.created(uri).body(evenementCree); // created = 201
    }

    /* ------------------ PATCH EDIT ------------------ */
    @PatchMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "L'événement a été modifié.",
                    content = {
                            @Content( mediaType = "application/json", schema = @Schema(implementation = Evenement.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Les informations envoyés sont incorrectes.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Evénement non trouvé", content = @Content)
    })
    public Evenement editEvenement(@PathVariable Integer id, @Valid @RequestBody Evenement evenement) {
        return evenementService.editEvenement(id, evenement); // created = 201
    }

    /* ------------------ PATCH - EDIT - EST SIGNALE ------------------ */
    @PatchMapping("/{id}/reported")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'événement a été modifié.", content = @Content),
            @ApiResponse(responseCode = "400", description = "L'id de l'événement passé dans l'url doit être un entier.", content = @Content),
    })
    public void updateEvenementEstSignale(@PathVariable Integer id, @RequestBody Evenement evenement) {
        evenementService.updateEvenementEstSuspendu(id, evenement);
    }

    /* ------------------ DELETE ------------------ */
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evénement supprimé",content = @Content),
            @ApiResponse(responseCode = "400", description = "L'id de l'événement passé dans l'url doit être un entier.", content = @Content),
    })
    public void deleteEvenement(@PathVariable Integer id) {
        evenementService.deleteEvenement(id);
    }
}
