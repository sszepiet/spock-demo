package pl.sii.eu.spockdemo.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> startOrder() {
        Order order = orderService.createOrder();
        URI locationUri = ServletUriComponentsBuilder.fromCurrentServletMapping()
                .path("orders/{uuid}")
                .build()
                .expand(order.getUuid())
                .toUri();
        return ResponseEntity.created(locationUri).build();
    }

    @RequestMapping("{uuid}")
    public Order getOrder(@PathVariable("uuid") String uuid) {
        return orderService.findById(uuid);
    }

    @RequestMapping(path = "{uuid}", method = RequestMethod.PUT)
    public ResponseEntity<Void> scanProduct(@PathVariable("uuid") String uuid, @Valid @RequestBody ProductReservation productReservation) {
        orderService.addProduct(uuid, productReservation);
        return ResponseEntity.noContent().build();
    }
}
