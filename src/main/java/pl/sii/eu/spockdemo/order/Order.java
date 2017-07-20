package pl.sii.eu.spockdemo.order;

import lombok.*;
import pl.sii.eu.spockdemo.product.Product;
import pl.sii.eu.spockdemo.reservation.Reservation;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Table(name = "ORDERS")
@Entity
@Setter(AccessLevel.PROTECTED)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "uuid")
public class Order {

    @Id
    private String uuid;

    @Column(nullable = false)
    private BigDecimal total = BigDecimal.ZERO;

    @OneToMany(mappedBy = "reservationId.order", cascade = CascadeType.ALL)
    private Set<Reservation> reservations = new HashSet<>();

    private Order(String uuid) {
        this.uuid = uuid;
    }

    public static Order of(String uuid) {
        return new Order(uuid);
    }

    public void reserveProduct(Product product, int requestedQuantity) {
        BigDecimal multipliedUnitPrice = product.getUnitPrice().multiply(BigDecimal.valueOf(requestedQuantity));
        this.total = total.add(multipliedUnitPrice);
        this.reservations.add(Reservation.ofOrderProductAndQuantity(this, product, requestedQuantity));
    }
}
