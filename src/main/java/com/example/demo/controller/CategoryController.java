package com.example.demo.controller;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.responses.ConfigResponse;
import com.example.demo.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ConfigResponse<CategoryDTO>> getCategories(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size
    ) {
        return ResponseEntity.ok(categoryService.getCategories(page, size));
    }
    @PostMapping
    public ResponseEntity<CategoryDTO> create(
            @RequestBody CategoryDTO categoryDTO
    ) {
        return ResponseEntity.ok(categoryService.create(categoryDTO));
    }
    @PutMapping
    public ResponseEntity<CategoryDTO> update(
            @RequestBody CategoryDTO categoryDTO
    ){
        return ResponseEntity.ok(categoryService.update(categoryDTO));
    }
}
