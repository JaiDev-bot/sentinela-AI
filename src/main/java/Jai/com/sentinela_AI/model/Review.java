package Jai.com.sentinela_AI.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;
    private LocalDateTime dataHora;
    private String sentimento;
    private String keyImportant;
    private String origem;
    private double sentimentoScore;

    public Review(Long id, String texto, LocalDateTime dataHora, String sentimento, String keyImportant, String origem, double sentimentoScore) {
        this.id = id;
        this.texto = texto;
        this.dataHora = dataHora;
        this.sentimento = sentimento;
        this.keyImportant = keyImportant;
        this.origem = origem;
        this.sentimentoScore = sentimentoScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getSentimento() {
        return sentimento;
    }

    public void setSentimento(String sentimento) {
        this.sentimento = sentimento;
    }

    public String getKeyImportant() {
        return keyImportant;
    }

    public void setKeyImportant(String keyImportant) {
        this.keyImportant = keyImportant;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public double getSentimentoScore() {
        return sentimentoScore;
    }

    public void setSentimentoScore(double sentimentoScore) {
        this.sentimentoScore = sentimentoScore;
    }
}

