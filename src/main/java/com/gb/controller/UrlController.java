package com.gb.controller;

import com.gb.entity.ShortUrl;
import com.gb.model.CreateShortUrlRequest;
import com.gb.service.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/urls")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService service;

    @PostMapping
    public ResponseEntity<ShortUrl> create(@Valid @RequestBody CreateShortUrlRequest request) {
        ShortUrl result = service.create(request);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{code}")
    public ResponseEntity<ShortUrl> get(@PathVariable String code) {
        return service.lookupActive(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
