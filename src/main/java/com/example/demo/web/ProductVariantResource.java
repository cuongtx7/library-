package com.example.demo.web;

import com.example.demo.dto.ProductVariantDTO;
import com.example.demo.service.ProductVariantService;
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
public class ProductVariantResource {
    @Autowired
    private ProductVariantService productService;

    @PostMapping("/product-variant")
    public ResponseEntity<ProductVariantDTO> createAccount(@Valid @RequestBody ProductVariantDTO customerDTO) throws URISyntaxException {
        if (customerDTO.getProductId() == null || customerDTO.getProductId().isEmpty()) {
            throw new RuntimeException("IdProduct null hoặc rỗng");
        }

        if (customerDTO.getDiscount() == null || customerDTO.getDiscount().equals("")) {
            throw new RuntimeException("Discount null hoặc rỗng");
        }

        if (customerDTO.getUnitPrice() == null || customerDTO.getUnitPrice().equals("")) {
            throw new RuntimeException("UnitPrice null hoặc rỗng");
        }
        ProductVariantDTO result = productService.save(customerDTO);
        return ResponseEntity.created(new URI("/api/product-variant/" + result.getId())).body(result);
    }

    @PutMapping("/product-variant")
    public ResponseEntity<ProductVariantDTO> updateAccount(@Valid @RequestBody ProductVariantDTO customerDTO) throws URISyntaxException {

        if (customerDTO.getId() == null || customerDTO.getId().isEmpty()) {
            throw new RuntimeException("id null hoặc rỗng");
        }
        if (customerDTO.getProductId() == null || customerDTO.getProductId().isEmpty()) {
            throw new RuntimeException("IdProduct null hoặc rỗng");
        }

        ProductVariantDTO result = productService.save(customerDTO);
        return ResponseEntity.created(new URI("/api/product-variant/" + result.getId())).body(result);
    }

    @GetMapping("/product-variant")
    public ResponseEntity<List<ProductVariantDTO>> getAllAccount(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {

        Page<ProductVariantDTO> page = productService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/product-variant/{id}")
    public ResponseEntity<ProductVariantDTO> getAccount(@PathVariable String id) {
        Optional<ProductVariantDTO> customerDTO = productService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customerDTO);
    }

    @DeleteMapping("//product-variant/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
