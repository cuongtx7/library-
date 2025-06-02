package com.example.demo.service.impl;

import com.example.demo.domain.Member;
import com.example.demo.dto.MenberDTO;
import com.example.demo.mapper.MenberMapper;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MenberMapper menberMapper;
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public MenberDTO save(MenberDTO menberDTO) {
        Member member = new Member();
        if (menberDTO.getId() == null) {
            menberDTO.setId(this.generateNextCategoryId());
            member = menberMapper.toEntity(menberDTO);
            member.setCreatedDate(Instant.now());
        } else {
            Optional<MenberDTO> menberDTO1 = this.findOne(menberDTO.getId());
            if (menberDTO1.isPresent()) {
                member = menberMapper.toEntity(menberDTO);
                member.setLastModifiedDate(Instant.now());

            } else {
                throw new RuntimeException("khong tồn tai bản ghi");
            }
        }
        member = memberRepository.save(member);
        return menberMapper.toDto(member);
    }

    @Override
    public Page<MenberDTO> findAll(Pageable pageable) {
        return memberRepository.findAll(pageable).map(menberMapper::toDto);
    }

    @Override
    public Optional<MenberDTO> findOne(String id) {
        return memberRepository.findById(id).map(menberMapper::toDto);
    }

    @Override
    public void delete(String id) {
        memberRepository.deleteById(id);
    }

    public String generateNextCategoryId() {
        String maxId = memberRepository.findMaxCode();
        int nextNum = 1;

        if (maxId != null && maxId.startsWith("M_")) {
            String numberPart = maxId.substring(3);
            try {
                nextNum = Integer.parseInt(numberPart) + 1;
            } catch (NumberFormatException e) {
            }
        }

        return String.format("M_%03d", nextNum);
    }
}
