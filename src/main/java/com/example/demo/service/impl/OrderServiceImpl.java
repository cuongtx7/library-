package com.example.demo.service.impl;

import com.example.demo.domain.Order;
import com.example.demo.domain.ProductVariant;
import com.example.demo.dto.*;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.AccountService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductVariantService productVariantService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderDTO save(OrderDTO orderDTO) {

        Optional<AccountDTO> accountDTO = null;
        try {
            accountDTO = accountService.findOne(orderDTO.getAccountId());
            if (!accountDTO.isPresent()) {
                throw new RuntimeException(orderDTO.getAccountId() + " không có thông tin thoong tin danwg nhap");
            }
        } catch (Exception e) {
            throw new RuntimeException("không lấy được thông tin đang nhập");
        }


        Optional<CustomerDTO> customerDTO = null;
        try {
            customerDTO = customerService.findOne(orderDTO.getCustomerId());
            if (!customerDTO.isPresent()) {
                throw new RuntimeException(orderDTO.getCustomerId() + " không có thông tin khach hang");
            }
        } catch (Exception e) {
            throw new RuntimeException("không lấy được thông khach hang");
        }

        Optional<ProductVariantDTO> productVariant = null;
        try {
            productVariant = productVariantService.findOne(orderDTO.getProductVariantId());
            if (!productVariant.isPresent()) {
                throw new RuntimeException(orderDTO.getCustomerId() + " không có thông tin san pham");
            }
        } catch (Exception e) {
            throw new RuntimeException("không lấy được thông san pham");
        }

        ProductVariantDTO productVariantDTO = productVariant.get();

        float unitPrice = productVariantDTO.getUnitPrice();
        float productTotal = orderDTO.getProductTotal();
        float discount = productVariantDTO.getDiscount();
        float amount = unitPrice * productTotal * (1 - discount / 100);

        LocalDateTime now = LocalDateTime.now();
        if (orderDTO.getId() == null) {
            orderDTO.setPaidAmount(amount);
            String newId = generateNewId();
            orderDTO.setStatus("processing");
            orderDTO.setId(newId);
            orderDTO.setCreatedBy(accountDTO.get().getEmail());
            orderDTO.setCreatedDate(now);
        } else {
            orderDTO.setLastModifiedBy(accountDTO.get().getEmail());
            orderDTO.setLastModifiedDate(now);
        }
        Order customer = orderMapper.toEntity(orderDTO);
        customer = orderRepository.save(customer);
        return orderMapper.toDto(customer);
    }

    @Override
    public Page<OrderDTO> findAll(SeachOrder seachOrder, Pageable pageable) {
        Optional<AccountDTO> account = null;
        try {
            account = accountService.findOne(seachOrder.getAccoutId());
            if (!account.isPresent()) {
                throw new RuntimeException(seachOrder + " không có thông tin thoong tin danwg nhap");
            }
            if (!account.get().getRole().equals("Admin")) {
                Page<OrderDTO> page = orderRepository.findByAccountId(seachOrder.getAccoutId(), pageable).map(orderMapper::toDto);
                return page;
            } else {
                Page<OrderDTO> page = orderRepository.findAll(pageable).map(orderMapper::toDto);
                return page;
            }
        } catch (Exception e) {
            throw new RuntimeException("không lấy được thông tin đang nhập");
        }

    }


    @Override
    public Optional<OrderDTO> findOne(String id) {
        return orderRepository.findById(id)
                .map(orderMapper::toDto);
    }

    @Override
    public void delete(String id) {
        orderRepository.deleteById(id);
    }

    private String generateNewId() {
        List<Order> list = orderRepository.findTopByIdOrderByIdDesc();
        if (list.isEmpty()) {
            return "OD001";
        } else {
            String lastId = list.get(0).getId();
            int num = Integer.parseInt(lastId.substring(2));
            num++;
            return String.format("OD%03d", num);
        }
    }
}
