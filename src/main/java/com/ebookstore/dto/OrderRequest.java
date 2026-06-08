package com.ebookstore.dto;

import com.ebookstore.entity.Order;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    @NotBlank(message = "Delivery address is required")
    private String deliveryAddress;

    @NotNull(message = "Payment method is required")
    private Order.PaymentMethod paymentMethod;

    private Integer useGiftPoints = 0;
}

// Made with Bob
