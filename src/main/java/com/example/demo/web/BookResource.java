package com.example.demo.web;

import com.example.demo.dto.BookDTO;
import com.example.demo.service.BookService;
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
public class BookResource {
    @Autowired
    private BookService bookService;

    @PostMapping("/creted-book")
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) {
        BookDTO result = bookService.save(bookDTO);
        return ResponseEntity.ok().body(result);
    }


    @PutMapping("/update-book")
    public ResponseEntity<BookDTO> updateBook(@Valid @RequestBody BookDTO bookDTO)
            throws URISyntaxException {
        if (bookDTO.getId() == null) {
            throw new RuntimeException("id null");
        }
        BookDTO result = bookService.save(bookDTO);
        return ResponseEntity.ok().body(result);
    }


    @GetMapping("/get-all-book")
    public ResponseEntity<List<BookDTO>> getAllBook(Pageable pageable,
                                                    @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        Page<BookDTO> page = bookService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    @GetMapping("/book/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable String id) {
        Optional<BookDTO> categoryDTO = bookService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categoryDTO);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
