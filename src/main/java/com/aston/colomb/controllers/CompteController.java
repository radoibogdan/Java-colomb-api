package com.aston.colomb.controllers;

import com.aston.colomb.entities.Compte;
import com.aston.colomb.services.CompteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/comptes")
public class CompteController {
    private final CompteService compteService;

    // Autowire du service dans le constructeur
    public CompteController(CompteService compteService) {
        this.compteService = compteService;
    }

    @GetMapping
    public List<Compte> findAll() {
        return compteService.getAllComptes();
    }

    // Le ResponseEntity.of renvoie un Body vide + 404 dans le cas d'un Optional vide, OR Body avec le Compte + 200
    @GetMapping("{id}")
    public ResponseEntity<Compte> findById(@PathVariable Integer id) {
        return ResponseEntity.of(compteService.findCompteById(id));
    }

    @PostMapping
    public ResponseEntity<Compte> saveCompte(@RequestBody Compte compte) {
        Compte compteCree = compteService.saveCompte(compte);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(compteCree.getId())
                .toUri();
        return ResponseEntity.created(uri).body(compteCree); // created = 201
    }
}
