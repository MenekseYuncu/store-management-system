package org.menekseyuncu.storemanagementsystem.customer.model.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import org.menekseyuncu.storemanagementsystem.cart.model.entity.CartEntity;
import org.menekseyuncu.storemanagementsystem.common.model.BaseEntity;
import org.menekseyuncu.storemanagementsystem.orders.model.entity.OrderEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "sm_customer")
public class CustomerEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CartEntity cart;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<OrderEntity> orders = new ArrayList<>();

}
