package com.example.demo.web;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.service.CustomerService;
import com.example.demo.services.ProductService;
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
public class ProductResource {
    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<ProductDTO> createAccount(@Valid @RequestBody ProductDTO customerDTO) throws URISyntaxException {
        if (customerDTO.getIdCategory() == null || customerDTO.getIdCategory().isEmpty()) {
            throw new RuntimeException("IdCategory null hoặc rỗng");
        }
        ProductDTO result = productService.save(customerDTO);
        return ResponseEntity.created(new URI("/api/product/" + result.getId())).body(result);
    }

    @PutMapping("/product")
    public ResponseEntity<ProductDTO> updateAccount(@Valid @RequestBody ProductDTO customerDTO) throws URISyntaxException {

        if (customerDTO.getId() == null || customerDTO.getId().isEmpty()) {
            throw new RuntimeException("id null hoặc rỗng");
        }
        if (customerDTO.getIdCategory() == null || customerDTO.getIdCategory().isEmpty()) {
            throw new RuntimeException("IdCategory null hoặc rỗng");
        }

        ProductDTO result = productService.save(customerDTO);
        return ResponseEntity.created(new URI("/api/product/" + result.getId())).body(result);
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductDTO>> getAllAccount(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {

        Page<ProductDTO> page = productService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getAccount(@PathVariable String id) {
        Optional<ProductDTO> customerDTO = productService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customerDTO);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
