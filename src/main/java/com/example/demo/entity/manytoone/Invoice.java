package com.example.demo.entity.manytoone;

import jakarta.persistence.*;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inv_id;
    private Long invoiceNumber;
    @ManyToOne
    Customer customer;

    public Long getId() {
        return inv_id;
    }

    public void setId(Long inv_id) {
        this.inv_id = inv_id;
    }

    public Long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
