package Jai.com.sentinela_AI.controller;

import Jai.com.sentinela_AI.model.Review;
import Jai.com.sentinela_AI.service.ReviewService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequestMapping("/api/reviews")
@RestController
public class ReviewController {

    private final ReviewService reviewService;


    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/analisar")
    public ResponseEntity<Review> analisar(@RequestBody String texto) {
        Review reviewSalva = reviewService.analisaESalva(texto, "Postman");
        return ResponseEntity.ok(reviewSalva);
    }

    @GetMapping
    public ResponseEntity<List<Review>> listarTodos() {

        List<Review> reviews = StreamSupport
                .stream(reviewService.buscarTodos().spliterator(), false)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/importar")
    public String importar() {

        new Thread(() -> {
            reviewService.importarReviews20k();
        }).start();

        return " Operação iniciada no servidor! Acompanhe os logs no IntelliJ ";
    }


}




