package com.example.demo.web;

import com.example.demo.dto.AccountDTO;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.Login;
import com.example.demo.service.CustomerService;
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
public class CustomerResource {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer")
    public ResponseEntity<CustomerDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO) throws URISyntaxException {

        CustomerDTO result = customerService.save(customerDTO);
        return ResponseEntity.created(new URI("/api/account/" + result.getId())).body(result);
    }

    @PutMapping("/customer")
    public ResponseEntity<CustomerDTO> updateAccount(@Valid @RequestBody CustomerDTO customerDTO) throws URISyntaxException {

        if (customerDTO.getId() == null || customerDTO.getId().isEmpty()) {
            throw new RuntimeException("id null hoặc rỗng");
        }

        CustomerDTO result = customerService.save(customerDTO);
        return ResponseEntity.created(new URI("/api/customer/" + result.getId())).body(result);
    }

    @GetMapping("/customer")
    public ResponseEntity<List<CustomerDTO>> getAllAccount(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {

        Page<CustomerDTO> page = customerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDTO> getAccount(@PathVariable String id) {
        Optional<CustomerDTO> customerDTO = customerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customerDTO);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
