package pl.sii.eu.spockdemo.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sii.eu.spockdemo.order.Order;
import pl.sii.eu.spockdemo.reservation.Reservation;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "PRODUCTS")
@Setter(AccessLevel.PROTECTED)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @NotNull
    private String uuid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private int availableQuantity;

    @JsonIgnore
    @OneToMany(mappedBy = "reservationId.reservedProduct")
    private Set<Reservation> reservations = new HashSet<>();

    private Product(String name, BigDecimal unitPrice, int availableQuantity) {
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.unitPrice = unitPrice;
        this.availableQuantity = availableQuantity;
    }

    public static Product ofNamePriceAndQuantity(String name, BigDecimal unitPrice, int availableQuantity) {
        return new Product(name, unitPrice, availableQuantity);
    }

}
