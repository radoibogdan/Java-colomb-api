package com.aston.colomb.controllers;

import com.aston.colomb.entities.Review;
import com.aston.colomb.services.ReviewService;
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

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    // Auto wiring of service in constructor
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /* ------------------ GET ALL ------------------ */
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Tous les avis ont été récupérés.",
                    content = {
                            @Content( mediaType = "application/json", schema = @Schema(implementation = Review.class))
                    })
    })
    public List<Review> findAll() {
        return reviewService.getAllReviews();
    }

//    /* ------------------ GET All by Id Entreprise ------------------ */
//    @GetMapping("/entreprise/{id}")
//    @ApiResponses(value = {
//            @ApiResponse( responseCode = "200", description = "Tous les avis entreprise ont été récupérés.",
//                    content = {
//                            @Content( mediaType = "application/json", schema = @Schema(implementation = Review.class))
//                    }),
//            @ApiResponse(responseCode = "400", description = "L'id entreprise passé dans l'url doit être un entier.", content = @Content)
//    })
//    public List<Review> findAllByEntrepriseId(@PathVariable Integer id) {
//        return reviewService.getAllReviewsEntreprise(id);
//    }


    /* ------------------ GET All Reviews Reported ------------------ */
    @GetMapping("/reported")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Tous les avis signalés ont été récupérés.",
                    content = {
                            @Content( mediaType = "application/json", schema = @Schema(implementation = Review.class))
                    })
    })
    public List<Review> findAllReported() {
        return reviewService.getAllReviewsReported();
    }


    /* ------------------ GET by Id ------------------ */
    // Le ResponseEntity.of renvoie un Body vide + 404 dans le cas d'un Optional vide, OR Body avec le Review + 200
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Avis trouvé",
                    content = {
                            @Content( mediaType = "application/json", schema = @Schema(implementation = Review.class))
                    }),
            @ApiResponse(responseCode = "400", description = "L'id passé dans l'url doit être un entier.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Avis non trouvé", content = @Content)
    })
    public ResponseEntity<Review> findById(@PathVariable Integer id) {
        return ResponseEntity.of(reviewService.findReviewById(id));
    }


    /* ------------------ POST CREATE ------------------ */
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse( responseCode = "201", description = "L'avis a été créé.",
                    content = {
                            @Content( mediaType = "application/json", schema = @Schema(implementation = Review.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Les informations envoyés sont incorrectes.", content = @Content),
    })
    public ResponseEntity<Review> saveReview(@Valid @RequestBody Review review) {
        Review reviewCree = reviewService.saveReview(review);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(reviewCree.getId())
                .toUri();
        return ResponseEntity.created(uri).body(reviewCree); // created = 201
    }


    /* ------------------ PATCH EDIT ------------------ */
    @PatchMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "L'avis a été modifié.",
                    content = {
                            @Content( mediaType = "application/json", schema = @Schema(implementation = Review.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Les informations envoyés sont incorrectes.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Avis non trouvé", content = @Content)
    })
    public Review editReview(@PathVariable Integer id, @Valid @RequestBody Review review) {
        return reviewService.editReview(id, review); // created = 201
    }


    /* ------------------ PATCH - EDIT - EST SIGNALE ------------------ */
    @PatchMapping("/{id}/reported")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'avis a été modifié.", content = @Content),
            @ApiResponse(responseCode = "400", description = "L'id de l'avis passé dans l'url doit être un entier.", content = @Content),
    })
    public void updateReviewEstSignale(@PathVariable Integer id, @RequestBody Review review) {
        reviewService.updateReviewEstSuspendu(id, review);
    }


    /* ------------------ DELETE ------------------ */
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Avis supprimé",content = @Content),
            @ApiResponse(responseCode = "400", description = "L'id de l'avis passé dans l'url doit être un entier.", content = @Content),
    })
    public void deleteReview(@PathVariable Integer id) {
        reviewService.deleteReview(id);
    }
}
