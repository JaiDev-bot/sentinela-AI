package Jai.com.sentinela_AI.service;


import Jai.com.sentinela_AI.model.Review;
import Jai.com.sentinela_AI.repository.ReviewRepository;
import com.azure.ai.textanalytics.TextAnalyticsClient;
import com.azure.ai.textanalytics.models.DocumentSentiment;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.RFC4180ParserBuilder;
import org.springframework.stereotype.Service;


import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.nimbusds.openid.connect.sdk.assurance.claims.ISO3166_1Alpha2CountryCode.AI;

@Service
public class ReviewService {


    private final ReviewRepository reviewRepository;
    private TextAnalyticsClient textAnalyticsClient;

    public ReviewService(ReviewRepository reviewRepository, TextAnalyticsClient textAnalyticsClient) {
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


    public void importar() {
        String caminhoArquivo = "C:/Users/jaian/IdeaProjects/sentinela-AI/data/olist_order_reviews_dataset.csv";

        try (com.opencsv.CSVReader reader = new com.opencsv.CSVReaderBuilder(new java.io.FileReader(caminhoArquivo))
                .withCSVParser(new com.opencsv.RFC4180ParserBuilder().withSeparator(',').build()).build()) {

            reader.readNext();
            String[] linha;
            int processados = 0;
            int meta = 30000;

            System.out.println(" Iniciando...");

            while (processados < meta) {
                linha = reader.readNext();
                if (linha == null) break;

                if (linha.length > 3) {
                    String comentario = linha[3].trim();

                    //filtro
                    if (!comentario.isEmpty() && comentario.length() > 10 && comentario.length() < 150) {

                        try {
                            Review review = new Review();
                            review.setId(java.util.UUID.randomUUID().toString());
                            review.setTexto(comentario);

                            // Chamada da IA com limite
                            String analise = textAnalyticsClient.analyzeSentiment(comentario).getSentiment().toString();
                            review.setSentimento(analise);

                            reviewRepository.save(review);
                            processados++;


                            Thread.sleep(200);

                            if (processados % 100 == 0) {
                                System.out.println("✅ [PROGRESSO] " + processados + " registros salvos. Crédito Azure respirando...");
                            }

                        } catch (Exception e) {
                            System.out.println(" Pulando um registro por erro: " + e.getMessage());
                            Thread.sleep(1000);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

}






