package com.example.demo.web;

import com.example.demo.cors.JwtTokenProvider;
import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.TokenDTO;
import com.example.demo.requests.filters.CategoryFilters;
import com.example.demo.responses.ConfigResponse;
import com.example.demo.service.CategoryService;
import com.example.demo.until.JwtUtil;
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
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @GetMapping
    public ResponseEntity<ConfigResponse<CategoryDTO>> getCategories(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            @ModelAttribute CategoryFilters categoryFilters
            ) {
        return ResponseEntity.ok(categoryService.getCategories(page, size, categoryFilters));
    }
    @PostMapping
    public ResponseEntity<CategoryDTO> create(
            @RequestBody CategoryDTO categoryDTO,
            @RequestHeader("Authorization") String authHeader
    ) {
        String token = JwtUtil.getToken(authHeader);
        TokenDTO tokenDTO = jwtTokenProvider.getUserInfo(token);
        return ResponseEntity.ok(categoryService.save(categoryDTO, tokenDTO));
    }
    @PutMapping
    public ResponseEntity<CategoryDTO> update(
            @RequestBody CategoryDTO categoryDTO,
            @RequestHeader("Authorization") String authHeader
    ) {
        String token = JwtUtil.getToken(authHeader);
        TokenDTO tokenDTO = jwtTokenProvider.getUserInfo(token);
        return ResponseEntity.ok(categoryService.update(categoryDTO, tokenDTO));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(
            @PathVariable String id) {
        return ResponseEntity.ok(categoryService.getCategory(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDTO> delete(
            @PathVariable String id,
            @RequestHeader("Authorization") String authHeader
    ){
        String token = JwtUtil.getToken(authHeader);
        TokenDTO tokenDTO = jwtTokenProvider.getUserInfo(token);
        return ResponseEntity.ok(categoryService.delete(id, tokenDTO));
    }
}
