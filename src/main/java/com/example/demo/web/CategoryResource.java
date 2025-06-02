package com.example.demo.web;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.service.CategoryService;
import io.github.jhipster.web.util.HeaderUtil;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<CategoryDTO> createCategory(
            @Valid @RequestBody CategoryDTO categoryDTO) throws URISyntaxException {
        if (categoryDTO.getCategoryName() == null && categoryDTO.getCategoryName().isEmpty()) {
            throw new RuntimeException("CategoryName null");
        }
        CategoryDTO result = categoryService.save(categoryDTO);
        return ResponseEntity.ok().body(result);
    }


    @PutMapping("/category")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO)
            throws URISyntaxException {
        if (categoryDTO.getId() == null) {
            throw new RuntimeException("id null");
        }
        CategoryDTO result = categoryService.save(categoryDTO);
        return ResponseEntity.ok().body(result);
    }


    @GetMapping("/get-all-category")
    public ResponseEntity<List<CategoryDTO>> getAllCategory(Pageable pageable,
                                                            @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        Page<CategoryDTO> page = categoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable String id) {
        Optional<CategoryDTO> categoryDTO = categoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categoryDTO);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
