package pl.sii.eu.spockdemo.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sii.eu.spockdemo.error.NotFoundException;
import pl.sii.eu.spockdemo.product.Product;
import pl.sii.eu.spockdemo.product.ProductRepository;

import java.util.UUID;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProductReservationValidator productReservationValidator;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, ProductReservationValidator productReservationValidator) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.productReservationValidator = productReservationValidator;
    }

    public Order createOrder() {
        return orderRepository.save(Order.of(UUID.randomUUID().toString()));
    }

    public Order findById(String uuid) {
        return orderRepository.findByUuid(uuid).orElseThrow(NotFoundException::new);
    }

    public void addProduct(String orderUuid, ProductReservation productReservation) {
        Order order = orderRepository.findByUuid(orderUuid).orElseThrow(NotFoundException::new);
        Product product = productRepository.findByUuid(productReservation.getProductId()).orElseThrow(NotFoundException::new);
        productReservationValidator.validateRequestQuantityIsAvailable(product, productReservation.getRequestedQuantity());
        order.reserveProduct(product, productReservation.getRequestedQuantity());
        orderRepository.save(order);
    }
}
