package pl.sii.eu.spockdemo.reservation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import pl.sii.eu.spockdemo.order.Order;
import pl.sii.eu.spockdemo.product.Product;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "RESERVATION")
@Setter(AccessLevel.PROTECTED)
@Getter
public class Reservation {

    @EmbeddedId
    private ReservationId reservationId;

    @Column(nullable = false)
    private int reservedQuantity;

    @Column(nullable = false)
    private LocalDateTime validUntil;

    public static Reservation ofOrderProductAndQuantity(Order order, Product product, int requestedQuantity) {
        Reservation reservation = new Reservation();
        reservation.setReservationId(new ReservationId(product, order));
        reservation.setReservedQuantity(requestedQuantity);
        reservation.setValidUntil(LocalDateTime.now().plusMinutes(30L));
        return reservation;
    }
}
