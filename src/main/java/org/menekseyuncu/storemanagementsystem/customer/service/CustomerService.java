package org.menekseyuncu.storemanagementsystem.customer.service;


import org.menekseyuncu.storemanagementsystem.customer.controller.request.CustomerAddRequest;

public interface CustomerService {

    void addCustomer(CustomerAddRequest request);
}
