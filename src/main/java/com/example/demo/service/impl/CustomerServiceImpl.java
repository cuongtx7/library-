package com.example.demo.service.impl;

import com.example.demo.domain.Customer;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        LocalDateTime now = LocalDateTime.now();
        Optional<Customer> account = null;

        if (customerDTO.getId() != null ) {
            customerDTO.setLastModifiedBy(null);
            customerDTO.setLastModifiedDate(now);
        } else {
            try {
                account = customerRepository.findByEmail(customerDTO.getEmail());
            } catch (Exception e) {
                throw new RuntimeException("email khong tồn tài trong hệ thống");
            }
            if (account.isPresent()) {
                throw new RuntimeException("Email đã tồn tài trong hệ thống");
            }
            try {
                account = customerRepository.findByPhone(customerDTO.getPhone());
            } catch (Exception e) {
                throw new RuntimeException("phone khong tồn tài trong hệ thống");
            }
            if (account.isPresent()) {
                throw new RuntimeException("phone đã tồn tài trong hệ thống");
            }
            String newId = generateNewId();
            customerDTO.setId(newId);
            customerDTO.setCreatedBy(null);
            customerDTO.setCreatedDate(now);
        }
        Customer customer = customerMapper.toEntity(customerDTO);
        customer = customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    @Override
    public Page<CustomerDTO> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable)
                .map(customerMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerDTO> findOne(String id) {
        return customerRepository.findById(id)
                .map(customerMapper::toDto);
    }

    @Override
    public void delete(String id) {
        customerRepository.deleteById(id);
    }

    private String generateNewId() {
        List<Customer> list = customerRepository.findTopByIdOrderByIdDesc();
        if (list.isEmpty()) {
            return "CT001";
        } else {
            String lastId = list.get(0).getId();
            int num = Integer.parseInt(lastId.substring(2));
            num++;
            return String.format("CT%03d", num);
        }
    }
}
