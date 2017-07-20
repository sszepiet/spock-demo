package pl.sii.eu.spockdemo.reservation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pl.sii.eu.spockdemo.order.Order;
import pl.sii.eu.spockdemo.product.Product;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
public class ReservationId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "uuid")
    private Product reservedProduct;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "uuid")
    private Order order;
}
