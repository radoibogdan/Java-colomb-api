package com.aston.colomb.controllers;

import com.aston.colomb.entities.Evenement;
import com.aston.colomb.services.EvenementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
    public List<Evenement> findAll() {
        return evenementService.getAllEvenements();
    }

    /* ------------------ GET All by Id Entreprise ------------------ */
    @GetMapping("/entreprise/{id}")
    public List<Evenement> findAllByEntrepriseId(@PathVariable Integer id) {
        return evenementService.getAllEvenementsEntreprise(id);
    }

    /* ------------------ GET All Evenements Reported ------------------ */
    @GetMapping("/reported")
    public List<Evenement> findAllReported() {
        return evenementService.getAllEvenementsReported();
    }

    /* ------------------ GET by Id ------------------ */
    // Le ResponseEntity.of renvoie un Body vide + 404 dans le cas d'un Optional vide, OR Body avec le Evenement + 200
    @GetMapping("/{id}")
    public ResponseEntity<Evenement> findById(@PathVariable Integer id) {
        return ResponseEntity.of(evenementService.findEvenementById(id));
    }

    /* ------------------ POST CREATE ------------------ */
    @PostMapping
    public ResponseEntity<Evenement> saveEvenement(@RequestBody Evenement compte) {
        Evenement compteCree = evenementService.saveEvenement(compte);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(compteCree.getId())
                .toUri();
        return ResponseEntity.created(uri).body(compteCree); // created = 201
    }

    /* ------------------ PATCH - EDIT - EST SIGNALE ------------------ */
    @PatchMapping("/{id}/reported")
    public void updateEvenementEstSignale(@PathVariable Integer id, @RequestBody Evenement evenement) {
        evenementService.updateEvenementEstSuspendu(id, evenement);
    }

    /* ------------------ DELETE ------------------ */
    @DeleteMapping("/{id}")
    public void deleteEvenement(@PathVariable Integer id) {
        evenementService.deleteEvenement(id);
    }
}
