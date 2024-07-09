package com.luanmelo.encurtadorurl.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "urls")
public class UrlEntity {

    @Id
    private String id;

    private String urlOriginal;

    @Indexed(expireAfterSeconds = 0)
    private LocalDateTime dataHoraExpiracao;

    public UrlEntity() {
    }

    public UrlEntity(String id, String urlOriginal, LocalDateTime dataHoraExpiracao) {
        this.id = id;
        this.urlOriginal = urlOriginal;
        this.dataHoraExpiracao = dataHoraExpiracao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlOriginal() {
        return urlOriginal;
    }

    public void setUrlOriginal(String urlOriginal) {
        this.urlOriginal = urlOriginal;
    }

    public LocalDateTime getDataHoraExpiracao() {
        return dataHoraExpiracao;
    }

    public void setDataHoraExpiracao(LocalDateTime dataHoraExpiracao) {
        this.dataHoraExpiracao = dataHoraExpiracao;
    }
}
