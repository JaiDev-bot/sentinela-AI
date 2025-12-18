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
        DocumentSentiment docSentiment = textAnalyticsClient.analyzeSentiment(text);


        double positivo = docSentiment.getConfidenceScores().getPositive();
        double neutro = docSentiment.getConfidenceScores().getNeutral();
        double negativo = docSentiment.getConfidenceScores().getNegative();

        Review review = new Review();
        review.setTexto(text);
        review.setOrigem(source);
        review.setDataHora(LocalDateTime.now());


        // o sentimento que a IA achou predominante (Positive, negative ou neutral)
        review.setSentimento(docSentiment.getSentiment().toString().toUpperCase());

        // o score do sentimento que venceu
        if (review.getSentimento().equals("POSITIVE")) review.setSentimentoScore(positivo);
        else if (review.getSentimento().equals("NEGATIVE")) review.setSentimentoScore(negativo);
        else review.setSentimentoScore(neutro);

        return reviewRepository.save(review);
    }
    }




