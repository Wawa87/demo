package com.example.demo.repository;

import com.example.demo.entity.manytoone.Customer;
import com.example.demo.entity.manytoone.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    public Invoice findByInvoiceNumber(Long invoiceNumber);
    public Invoice findByCustomer(Customer customer);
}
