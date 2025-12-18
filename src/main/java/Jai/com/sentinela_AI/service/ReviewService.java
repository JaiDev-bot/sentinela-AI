package Jai.com.sentinela_AI.service;


import Jai.com.sentinela_AI.model.Review;
import Jai.com.sentinela_AI.repository.ReviewRepository;
import com.azure.ai.textanalytics.TextAnalyticsClient;
import com.azure.ai.textanalytics.models.DocumentSentiment;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ReviewService {


    private final ReviewRepository reviewRepository;
    private TextAnalyticsClient textAnalyticsClient;

    public ReviewService(ReviewRepository reviewRepository, TextAnalyticsClient textAnalyticsClient){
        this.reviewRepository = reviewRepository;
        this.textAnalyticsClient = textAnalyticsClient;
    }


    public Review analisaESalva(String text, String source) {

        DocumentSentiment docSentiment = textAnalyticsClient.analyzeSentiment(text, "pt");


        Review review = new Review();

        // se o @GeneratedValue não funcionar, o ID gera aqui
        review.setId(UUID.randomUUID().toString());

        review.setTexto(text);
        review.setDataHora(LocalDateTime.now());


        String sentimentoFinal = docSentiment.getSentiment().toString().toUpperCase();
        review.setSentimento(sentimentoFinal);

        //  Atribui os scores detalhados
        review.setScorePositivo(docSentiment.getConfidenceScores().getPositive());
        review.setScoreNegativo(docSentiment.getConfidenceScores().getNegative());


        return reviewRepository.save(review);
    }

    public Iterable<Review> buscarTodos() {
        return reviewRepository.findAll();
    }


}





