package pl.sii.eu.spockdemo.order;

import lombok.Data;

@Data
public class ProductReservation {
    private String productId;
    private int requestedQuantity;
}
