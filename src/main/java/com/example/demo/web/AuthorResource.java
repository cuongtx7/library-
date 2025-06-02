package com.example.demo.web;

import com.example.demo.dto.AuthorDTO;
import com.example.demo.dto.BookDTO;
import com.example.demo.service.AuthorService;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthorResource {
    @Autowired
    private AuthorService authorService;

    @PostMapping("/author")
    public ResponseEntity<AuthorDTO> createBook(@Valid @RequestBody AuthorDTO authorDTO) {
        AuthorDTO result = authorService.save(authorDTO);
        return ResponseEntity.ok().body(result);
    }


    @PutMapping("/author")
    public ResponseEntity<AuthorDTO> updateBook(@Valid @RequestBody AuthorDTO authorDTO)
            throws URISyntaxException {
        if (authorDTO.getId() == null) {
            throw new RuntimeException("id null");
        }
        AuthorDTO result = authorService.save(authorDTO);
        return ResponseEntity.ok().body(result);
    }


    @GetMapping("/get-all-author")
    public ResponseEntity<List<AuthorDTO>> getAllBook(Pageable pageable,
                                                      @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        Page<AuthorDTO> page = authorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    @GetMapping("/author/{id}")
    public ResponseEntity<AuthorDTO> getBook(@PathVariable String id) {
        Optional<AuthorDTO> categoryDTO = authorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categoryDTO);
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
