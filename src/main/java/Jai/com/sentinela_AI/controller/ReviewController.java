package Jai.com.sentinela_AI.controller;

import Jai.com.sentinela_AI.model.Review;
import Jai.com.sentinela_AI.service.ReviewService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




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

}


