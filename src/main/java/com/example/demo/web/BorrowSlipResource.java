package com.example.demo.web;

import com.example.demo.dto.BorrowSlipDTO;
import com.example.demo.service.BorrowSlipService;
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
public class BorrowSlipResource {

    @Autowired
    private BorrowSlipService borrowSlipService;

    @PostMapping("/borrow-slip")
    public ResponseEntity<BorrowSlipDTO> createBorrowSlip(
            @Valid @RequestBody BorrowSlipDTO borrowSlipDTO)  throws URISyntaxException {
        if (borrowSlipDTO.getBookId() == null && borrowSlipDTO.getBookId().isEmpty()) {
            throw new RuntimeException("BookId null");
        }
        if (borrowSlipDTO.getMemberId() == null && borrowSlipDTO.getMemberId().isEmpty()) {
            throw new RuntimeException("MemberId null");
        }
        if (borrowSlipDTO.getLibrarianId() == null && borrowSlipDTO.getLibrarianId().isEmpty()) {
            throw new RuntimeException("LibrarianId null");
        }
        BorrowSlipDTO result = borrowSlipService.save(borrowSlipDTO);
        return ResponseEntity.ok().body(result);
    }


    @PutMapping("/borrow-slip")
    public ResponseEntity<BorrowSlipDTO> updateBorrowSlip(@Valid @RequestBody BorrowSlipDTO borrowSlipDTO) {
        if (borrowSlipDTO.getId() == null) {
            throw new RuntimeException("SlipId null");
        }
        BorrowSlipDTO result = borrowSlipService.save(borrowSlipDTO);
        return ResponseEntity.ok().body(result);
    }


    @GetMapping("/get-all-borrow-slip")
    public ResponseEntity<List<BorrowSlipDTO>> getAllBorrowSlip(Pageable pageable,
                                                                @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        Page<BorrowSlipDTO> page = borrowSlipService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    @GetMapping("/borrow-slip/{id}")
    public ResponseEntity<BorrowSlipDTO> getBorrowSlip(@PathVariable String id) {
        Optional<BorrowSlipDTO> borrowSlipDTO = borrowSlipService.findOne(id);
        return ResponseUtil.wrapOrNotFound(borrowSlipDTO);
    }

    @DeleteMapping("/borrowSlip/{id}")
    public ResponseEntity<Void> deleteBorrowSlip(@PathVariable String id) {
        borrowSlipService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
