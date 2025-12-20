package Jai.com.sentinela_AI.model;





import java.time.LocalDateTime;


import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import com.opencsv.bean.CsvBindByName;
import org.springframework.data.annotation.Id;


@Container(containerName = "Reviews")
public class Review {

    @Id
    @PartitionKey
    private String id;

    @CsvBindByName(column = "review_comment_message")
    private String texto;
    private String sentimento;
    private Double scorePositivo;
    private Double scoreNegativo;
    private LocalDateTime dataHora;
    private String origem;

    public Review(String id, String texto, String sentimento, Double scorePositivo, Double scoreNegativo, LocalDateTime dataHora, String origem) {
        this.id = id;
        this.texto = texto;
        this.sentimento = sentimento;
        this.scorePositivo = scorePositivo;
        this.scoreNegativo = scoreNegativo;
        this.dataHora = dataHora;
        this.origem = origem;
    }

    public Review(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getSentimento() {
        return sentimento;
    }

    public void setSentimento(String sentimento) {
        this.sentimento = sentimento;
    }

    public Double getScorePositivo() {
        return scorePositivo;
    }

    public void setScorePositivo(Double scorePositivo) {
        this.scorePositivo = scorePositivo;
    }

    public Double getScoreNegativo() {
        return scoreNegativo;
    }

    public void setScoreNegativo(Double scoreNegativo) {
        this.scoreNegativo = scoreNegativo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }
}

