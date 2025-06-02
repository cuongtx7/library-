package com.example.demo.web;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.LibrarianDTO;
import com.example.demo.service.CategoryService;
import com.example.demo.service.LibrarianService;
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
public class librarianResource {
    @Autowired
    private LibrarianService librarianService;

    @PostMapping("/librarian")
    public ResponseEntity<LibrarianDTO> createCategory(
            @Valid @RequestBody LibrarianDTO librarianDTO) throws URISyntaxException {
        if (librarianDTO.getUsername() == null && librarianDTO.getUsername().isEmpty()) {
            throw new RuntimeException("Name null");
        }

        LibrarianDTO result = librarianService.save(librarianDTO);
        return ResponseEntity.ok().body(result);
    }


    @PutMapping("/librarian")
    public ResponseEntity<LibrarianDTO> updateCategory(@Valid @RequestBody LibrarianDTO librarianDTO)
            throws URISyntaxException {
        if (librarianDTO.getId() == null) {
            throw new RuntimeException("id null");
        }
        LibrarianDTO result = librarianService.save(librarianDTO);
        return ResponseEntity.ok().body(result);
    }


    @GetMapping("/get-all-librarian")
    public ResponseEntity<List<LibrarianDTO>> getAllCategory(Pageable pageable,
                                                             @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        Page<LibrarianDTO> page = librarianService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    @GetMapping("/librarian/{id}")
    public ResponseEntity<LibrarianDTO> getCategory(@PathVariable String id) {
        Optional<LibrarianDTO> categoryDTO = librarianService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categoryDTO);
    }

    @DeleteMapping("/librarian/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        librarianService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
