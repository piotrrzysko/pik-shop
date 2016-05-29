package pl.elka.pw.pik.shop.dto;

import pl.elka.pw.pik.shop.domain.model.orders.DeliveryFormType;

import java.math.BigDecimal;

public class DeliveryFormTypeData {
    private DeliveryFormType id;
    private BigDecimal deliveryValue;
    private String name;

    public DeliveryFormTypeData() {}

    public DeliveryFormTypeData(DeliveryFormType deliveryFormType) {
        if (deliveryFormType != null) {
            this.id = deliveryFormType;
            this.deliveryValue = deliveryFormType.getPrice();
            this.name = deliveryFormType.getDisplayText();
        }
    }

    public DeliveryFormType getId() {
        return id;
    }

    public BigDecimal getDeliveryValue() {
        return deliveryValue;
    }

    public String getName() {
        return name;
    }
}
