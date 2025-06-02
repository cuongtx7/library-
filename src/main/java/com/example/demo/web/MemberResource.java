package com.example.demo.web;

import com.example.demo.dto.MenberDTO;
import com.example.demo.service.MemberService;
import io.github.jhipster.web.util.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import io.github.jhipster.web.util.PaginationUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MemberResource {
    @Autowired
    private MemberService memberService;

    @PostMapping("/create-member")
    public ResponseEntity<MenberDTO> createMenber(@RequestBody MenberDTO menberDTO) {
        if (menberDTO.getEmail() == null && menberDTO.getEmail().isEmpty()) {
            throw new RuntimeException("email null");
        }
        if (menberDTO.getPhoneNumber() == null && menberDTO.getPhoneNumber().isEmpty()) {
            throw new RuntimeException("Phone null");
        }
        if (menberDTO.getMembershipType() == null && menberDTO.getMembershipType().isEmpty()) {
            throw new RuntimeException("MemberId null");
        }

        MenberDTO result = memberService.save(menberDTO);
        return ResponseEntity.ok().body(result);
    }


    @PutMapping("/update-member")
    public ResponseEntity<MenberDTO> updateMenber(@Valid @RequestBody MenberDTO menberDTO) {
        if (menberDTO.getId() == null && menberDTO.getId().isEmpty()) {
            throw new RuntimeException("id null");
        }
        MenberDTO result = memberService.save(menberDTO);
        return ResponseEntity.ok().body(result);
    }


    @GetMapping("/get-all-member")
    public ResponseEntity<List<MenberDTO>> getAllMenber(Pageable pageable,
                                                        @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        Page<MenberDTO> page = memberService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    @GetMapping("/member/{id}")
    public ResponseEntity<MenberDTO> getMenber(@PathVariable String id) {
        Optional<MenberDTO> borrowSlipDTO = memberService.findOne(id);
        return ResponseUtil.wrapOrNotFound(borrowSlipDTO);
    }

    @DeleteMapping("/member/{id}")
    public ResponseEntity<Void> deleteMenber(@PathVariable String id) {
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
