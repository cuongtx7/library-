package com.example.demo.web;

import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.SeachOrder;
import com.example.demo.service.OrderService;
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
@RequestMapping("/api/oder")
public class OderResoure {
    @Autowired
    private OrderService orderService;

    @PostMapping()
    public ResponseEntity<OrderDTO> createAccount(@Valid @RequestBody OrderDTO customerDTO) throws URISyntaxException {
        if (customerDTO.getAccountId() == null || customerDTO.getAccountId().isEmpty()) {
            throw new RuntimeException("getAccountId null hoặc rỗng");
        }
        if (customerDTO.getCustomerId() == null || customerDTO.getCustomerId().isEmpty()) {
            throw new RuntimeException("getCustomerId null hoặc rỗng");
        }
        if (customerDTO.getProductVariantId() == null || customerDTO.getProductVariantId().isEmpty()) {
            throw new RuntimeException("getProductVariantId null hoặc rỗng");
        }
        OrderDTO result = orderService.save(customerDTO);
        return ResponseEntity.created(new URI("/api/product-variant/" + result.getId())).body(result);
    }

    @PutMapping()
    public ResponseEntity<OrderDTO> updateAccount(@Valid @RequestBody OrderDTO customerDTO) throws URISyntaxException {

        if (customerDTO.getId() == null || customerDTO.getId().isEmpty()) {
            throw new RuntimeException("id null hoặc rỗng");
        }
        if (customerDTO.getAccountId() == null || customerDTO.getAccountId().isEmpty()) {
            throw new RuntimeException("getAccountId null hoặc rỗng");
        }
        if (customerDTO.getCustomerId() == null || customerDTO.getCustomerId().isEmpty()) {
            throw new RuntimeException("getCustomerId null hoặc rỗng");
        }
        if (customerDTO.getProductVariantId() == null || customerDTO.getProductVariantId().isEmpty()) {
            throw new RuntimeException("getProductVariantId null hoặc rỗng");
        }

        OrderDTO result = orderService.save(customerDTO);
        return ResponseEntity.created(new URI("/api/product-variant/" + result.getId())).body(result);
    }

    @GetMapping()
    public ResponseEntity<List<OrderDTO>> getAllAccount(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder,
                                                     @RequestBody SeachOrder seachOrder) {

        Page<OrderDTO> page = orderService.findAll(seachOrder, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getAccount(@PathVariable String id) {
        Optional<OrderDTO> customerDTO = orderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
