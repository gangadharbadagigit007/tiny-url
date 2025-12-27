package com.gb.controller;

import com.gb.entity.ShortUrl;
import com.gb.service.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RedirectController {

    private final UrlService service;

    @GetMapping("/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code) {
        log.info("Redirecting to url");
        Optional<ShortUrl> optionalUrl = service.lookupActive(code);
        if (optionalUrl.isPresent()) {
            ShortUrl url = optionalUrl.get();
            service.registerHit(url);
            return ResponseEntity.status(302)
                    .header(HttpHeaders.LOCATION, url.getTargetUrl())
                    .build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


