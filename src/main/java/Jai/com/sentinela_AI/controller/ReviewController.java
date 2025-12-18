package Jai.com.sentinela_AI.controller;

import Jai.com.sentinela_AI.model.Review;
import Jai.com.sentinela_AI.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;

@RestController
public class ReviewController {

    private final ReviewService reviewService;


    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Review> criarAnalise(@RequestBody Map<String, String> payload) {
        String texto = payload.get("text");
        String origem = payload.getOrDefault("source", "WEB");

        Review resultado = reviewService.AnalisaESalva(texto, origem);

        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }
}


