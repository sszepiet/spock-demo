package pl.sii.eu.spockdemo.order

import pl.sii.eu.spockdemo.error.InsufficientStockException
import pl.sii.eu.spockdemo.product.Product
import pl.sii.eu.spockdemo.reservation.Reservation
import spock.lang.Specification

class ProductReservationValidatorTest extends Specification {

    ProductReservationValidator validator = new ProductReservationValidator();

    def "should pass validation when requested quantity is available"() {
        given:
        Product product = new Product(reservations: [new Reservation(reservedQuantity: 5), new Reservation(reservedQuantity: 2)], availableQuantity: 12)
        when:
        validator.validateRequestQuantityIsAvailable(product, 5)
        then:
        notThrown(InsufficientStockException)
    }

    def "should not pass validation when requested quantity is not available"() {
        given:
        Product product = new Product(reservations: [new Reservation(reservedQuantity: 5), new Reservation(reservedQuantity: 2)], availableQuantity: 12)
        when:
        validator.validateRequestQuantityIsAvailable(product, 6)
        then:
        def exception = thrown(InsufficientStockException)
        exception.message == "We're out of stock"
    }
}
