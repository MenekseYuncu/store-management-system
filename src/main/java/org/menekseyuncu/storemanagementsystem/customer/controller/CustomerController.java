package org.menekseyuncu.storemanagementsystem.customer.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.menekseyuncu.storemanagementsystem.common.responses.BaseResponse;
import org.menekseyuncu.storemanagementsystem.customer.controller.request.CustomerAddRequest;
import org.menekseyuncu.storemanagementsystem.customer.service.CustomerService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public BaseResponse<Void> addCustomer(
           @Valid @RequestBody CustomerAddRequest customerAddRequest
    ){
        customerService.addCustomer(customerAddRequest);
        return BaseResponse.SUCCESS;
    }
}
