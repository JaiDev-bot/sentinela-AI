package Jai.com.sentinela_AI.service;


import Jai.com.sentinela_AI.model.Review;
import Jai.com.sentinela_AI.repository.ReviewRepository;
import com.azure.ai.textanalytics.TextAnalyticsClient;
import com.azure.ai.textanalytics.models.DocumentSentiment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReviewService {


    private final ReviewRepository reviewRepository;
    private TextAnalyticsClient textAnalyticsClient;

    public ReviewService(ReviewRepository reviewRepository, TextAnalyticsClient textAnalyticsClient){
        this.reviewRepository = reviewRepository;
        this.textAnalyticsClient = textAnalyticsClient;
    }


    public Review AnalisaESalva (String text, String source) {
        //  Sentimento (Puxa do Azure)
        DocumentSentiment docSentiment = textAnalyticsClient.analyzeSentiment(text);


        String frases = String.join(", ", textAnalyticsClient.extractKeyPhrases(text));


        Review review = new Review();
        review.setTexto(text);
        review.setOrigem(source);
        review.setDataHora(LocalDateTime.now());
        review.setKeyImportant(frases);


        review.setSentimento(docSentiment.getSentiment().toString());
        review.setSentimentoScore(docSentiment.getConfidenceScores().getPositive());


        return reviewRepository.save(review);
    }



}
