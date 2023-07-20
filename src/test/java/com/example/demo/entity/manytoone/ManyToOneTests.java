package com.example.demo.entity.manytoone;

import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.InvoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class ManyToOneTests {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    public void beforeEachTest() {
        invoiceRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @Test
    public void testSaveCustomer() {
        Customer customer = new Customer();
        customer.setName("Jerry");
        customerRepository.save(customer);

        Assert.isTrue(customer.getId() != null, "Customer id != null after persist.");

        Customer customer1 = customerRepository.findByNameIgnoreCase("jerry");

        Assert.isTrue(customer1.getId() != null, "Customer id != null after retrieval.");
    }

    @Test
    public void testSaveInvoiceCascadeCustomer() {
        Customer customer = new Customer();
        customer.setName("Jerry");
        customerRepository.save(customer);

        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber(123L);
        invoice.setCustomer(customer);
        invoiceRepository.save(invoice);

        Assert.isTrue(invoice.getId() != null, "Invoice id set after persist.");

        Customer customer1 = invoiceRepository.findByInvoiceNumber(123L).getCustomer();

        Assert.isTrue(customer1.getId() != null, "Customer retrieved through Order query.");
    }
}
