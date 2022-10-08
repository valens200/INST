package com.example.demo.repository;

import com.example.demo.models.Customer;
import com.example.demo.utils.OrderResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface CustomerRepository  extends JpaRepository<Customer, Integer> {
    @Query("SELECT new com.example.demo.utils.OrderResponse(c.customerName, p.productName ) FROM Customer c JOIN c.products p")
    public Collection<OrderResponse> getJOin();
}
