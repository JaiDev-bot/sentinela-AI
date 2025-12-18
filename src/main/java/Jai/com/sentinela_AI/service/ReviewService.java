package Jai.com.sentinela_AI.service;


import Jai.com.sentinela_AI.model.Review;
import Jai.com.sentinela_AI.repository.ReviewRepository;
import com.azure.ai.textanalytics.TextAnalyticsClient;
import com.azure.ai.textanalytics.models.DocumentSentiment;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;


import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.nimbusds.openid.connect.sdk.assurance.claims.ISO3166_1Alpha2CountryCode.AI;

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

    public void importarReviews(String caminhoArquivo) {

        try (CSVReader reader = new CSVReader(new FileReader("C:/Users/jaian/IdeaProjects/sentinela-AI/data/olist_order_reviews_dataset.csv"));) {
            String[] linha;
            reader.readNext(); //Pula o cabeçalho

            int processados = 0;
            int meta = 3;

            while ((linha = reader.readNext()) != null && processados < meta) {

                if (linha.length > 3) {
                    String comentario = linha[3];

                    if (comentario != null && comentario.length() > 10) {
                        try {
                            // Chama  lógica da IA e Cosmos
                            analisaESalva(comentario, "Olist_Real");
                            processados++;
                            if (processados % 10 == 0) {
                                System.out.println(" Progresso: " + processados + "/" + meta);
                            }
                        } catch (Exception e) {

                            continue;
                        }
                    }
                }
            }
            System.out.println("Finalizado! " + processados + " reviews enviadas para o Azure Cosmos DB.");
        } catch (Exception e) {
            System.err.println(" Erro ao abrir o CSV: " + e.getMessage());
        }
    }


}





