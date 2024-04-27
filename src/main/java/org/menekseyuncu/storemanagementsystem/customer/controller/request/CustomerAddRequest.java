package org.menekseyuncu.storemanagementsystem.customer.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CustomerAddRequest(

        @NotNull
        @Size(min = 2, max = 300)
        String name,
        @NotNull
        @Email
        @Size(min = 10, max = 300)
        String email,
        @NotNull
        @Size(min = 2, max = 500)
        String address
) {
}
