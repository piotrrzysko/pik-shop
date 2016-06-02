package pl.elka.pw.pik.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.elka.pw.pik.shop.dto.DeliveryFormTypeData;
import pl.elka.pw.pik.shop.dto.OrderData;
import pl.elka.pw.pik.shop.dto.OrderItemData;
import pl.elka.pw.pik.shop.dto.PaymentTypeData;
import pl.elka.pw.pik.shop.services.CartService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping({"/orders"})
public class OrderController {
    private CartService cartService;

    @Autowired
    public OrderController(CartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderData getOrder(@CookieValue(value="orderId", required=false) Long orderId) {
        return cartService.findOrder(orderId);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addItem(@CookieValue(value="orderId", required=false) Long orderId, HttpServletResponse response,
                        @RequestBody OrderItemData item) {
        cartService.addItem(item, orderId, response);
    }

    @RequestMapping(value = "/{orderItemId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderData deleteItem(@CookieValue(value="orderId", required=false) Long orderId, @PathVariable Long orderItemId) {
        return cartService.deleteItem(orderItemId, orderId);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderData updateOrder(@CookieValue(value="orderId", required=false) Long orderId, @RequestBody OrderData order) {
        return cartService.updateOrder(orderId, order);
    }

    @RequestMapping(value = "/setStatusConfirmed", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void setStatusConfirmed(@CookieValue(value="orderId", required=false) Long orderId) {
        cartService.setStatusConfirmed(orderId);
    }

    @RequestMapping(value = "paymentTypes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PaymentTypeData> getPaymentTypes() {
        return cartService.getPaymentTypes();
    }

    @RequestMapping(value = "deliveryFormTypes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeliveryFormTypeData> getDeliveryFormTypes() {
        return cartService.getDeliveryFormTypes();
    }
}
