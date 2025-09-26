package com.example.demo.web;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.service.CategoryService;
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
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


//    @GetMapping
//    public ResponseEntity<ConfigResponse<CategoryDTO>> getCategories(
//            @RequestParam(value = "page", required = false) Integer page,
//            @RequestParam(value = "size", required = false) Integer size
//    ) {
//        return ResponseEntity.ok(categoryService.getCategories(page, size));
//    }
//    @PostMapping
//    public ResponseEntity<CategoryDTO> create(
//            @RequestBody CategoryDTO categoryDTO
//    ) {
//        return ResponseEntity.ok(categoryService.create(categoryDTO));
//    }
//    @PutMapping
//    public ResponseEntity<CategoryDTO> update(
//            @RequestBody CategoryDTO categoryDTO
//    ){
//        return ResponseEntity.ok(categoryService.update(categoryDTO));
//    }

    @PostMapping("/category")
    public ResponseEntity<CategoryDTO> createAccount(@Valid @RequestBody CategoryDTO customerDTO) throws URISyntaxException {
        CategoryDTO result = categoryService.save(customerDTO);
        return ResponseEntity.created(new URI("/api/product/" + result.getId())).body(result);
    }

    @PutMapping("/category")
    public ResponseEntity<CategoryDTO> updateAccount(@Valid @RequestBody CategoryDTO customerDTO) throws URISyntaxException {

        if (customerDTO.getId() == null || customerDTO.getId().isEmpty()) {
            throw new RuntimeException("id null hoặc rỗng");
        }

        CategoryDTO result = categoryService.save(customerDTO);
        return ResponseEntity.created(new URI("/api/product/" + result.getId())).body(result);
    }

    @GetMapping("/category")
    public ResponseEntity<List<CategoryDTO>> getAllAccount(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {

        Page<CategoryDTO> page = categoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDTO> getAccount(@PathVariable String id) {
        Optional<CategoryDTO> customerDTO = categoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customerDTO);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
