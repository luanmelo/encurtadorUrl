package com.luanmelo.encurtadorurl.controller;

import com.luanmelo.encurtadorurl.dto.EncurtadorUrlResponse;
import com.luanmelo.encurtadorurl.entities.UrlEntity;
import com.luanmelo.encurtadorurl.repository.UrlRepository;
import com.luanmelo.encurtadorurl.dto.EncurtadorUrlRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
public class UrlController {

    private final UrlRepository urlRepository;

    public UrlController(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @PostMapping(value = "/encurtar-url")
    public ResponseEntity<EncurtadorUrlResponse> encurtadorUrl(@RequestBody EncurtadorUrlRequest urlRequest,
                                                               HttpServletRequest servletRequest){

        String id;
        do {
            id = RandomStringUtils.randomAlphabetic(5,10);
        }while (urlRepository.existsById(id));

        urlRepository.save(new UrlEntity(id, urlRequest.url(), LocalDateTime.now().plusMinutes(1)));

        var redirectUrl = servletRequest.getRequestURL().toString().replace("encurtar-url", id);

        return ResponseEntity.ok(new EncurtadorUrlResponse(redirectUrl));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Void> find(@PathVariable("id") String id){
        var urlOriginal = urlRepository.findById(id);

        if(urlOriginal.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(urlOriginal.get().getUrlOriginal()));

        return ResponseEntity.status(HttpStatus.FOUND).headers(httpHeaders).build();
    }
}
