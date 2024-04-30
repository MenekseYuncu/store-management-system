create table if not exists sm_customer
(
    id         bigserial
        constraint pk__sm_customer__id primary key,
    name       varchar(250)                     not null,
    email      varchar(200)
        constraint u__sm_customer__email unique not null,
    address    varchar(300)                     not null,
    created_at timestamp(0)                     not null,
    updated_at timestamp(0),
    deleted_at timestamp(0)
);

create table if not exists sm_product
(
    id             bigserial
        constraint pk__sm_product__id primary key,
    name           varchar(300)   not null,
    price          numeric(50, 3) not null,
    stock_quantity bigserial      not null,
    created_at     timestamp(0)   not null,
    updated_at     timestamp(0),
    deleted_at     timestamp(0)
);

create table if not exists sm_cart
(
    id          bigserial
        constraint pk__sm_cart__id primary key,
    customer_id bigserial
        constraint fk__sm_cart__customer__id references sm_customer,
    total_price numeric(50, 3) not null,
    created_at  timestamp(0)   not null,
    updated_at  timestamp(0),
    deleted_at  timestamp(0)

);

create table if not exists sm_cart_item
(
    id         bigserial
        constraint pk__sm_cart_item__id primary key,
    cart_id    bigint
        constraint fk__sm_cart_item__cart__id references sm_cart,
    product_id bigint
        constraint fk__sm_cart_item__product__id references sm_product,
    quantity   bigserial    not null,
    created_at timestamp(0) not null,
    updated_at timestamp(0),
    deleted_at timestamp(0)
);

create table if not exists sm_order
(
    id          bigserial
        constraint pk__sm_order__id primary key,
    customer_id bigint
        constraint fk__sm_order__customer__id references sm_customer,
    total_price numeric(50, 3) not null,
    created_at  timestamp(0)   not null,
    updated_at  timestamp(0),
    deleted_at  timestamp(0)
);

create table if not exists sm_order_item
(
    id         bigserial
        constraint pk__sm_order_item__id primary key,
    order_id   bigint
        constraint fk__sm_order_item__order__id references sm_order,
    product_id bigint
        constraint fk__sm_order_item__product__id references sm_product,
    quantity   bigserial      not null,
    unit_price numeric(50, 3) not null,
    created_at timestamp(0)   not null,
    updated_at timestamp(0),
    deleted_at timestamp(0)

);

create table if not exists sm_product_price_history
(
    id         bigserial
        constraint pk__sm_product_price_history__id primary key,
    product_id bigint
        constraint fk__sm_product_price_history__product__id references sm_product,
    old_price  numeric(50, 3) not null,
    new_price  numeric(50, 3) not null,
    updated_at timestamp(0)
);

