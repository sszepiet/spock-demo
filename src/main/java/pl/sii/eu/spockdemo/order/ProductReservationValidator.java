package pl.sii.eu.spockdemo.order;

import org.springframework.stereotype.Component;
import pl.sii.eu.spockdemo.error.InsufficientStockException;
import pl.sii.eu.spockdemo.product.Product;
import pl.sii.eu.spockdemo.reservation.Reservation;

@Component
public class ProductReservationValidator {

    public void validateRequestQuantityIsAvailable(Product product, int requestedQuantity) {
        int alreadyReserved = product.getReservations().stream()
                .mapToInt(Reservation::getReservedQuantity)
                .sum();
        if (alreadyReserved + requestedQuantity > product.getAvailableQuantity()) {
            throw new InsufficientStockException();
        }
    }
}
