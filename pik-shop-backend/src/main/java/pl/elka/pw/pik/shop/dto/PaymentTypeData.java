package pl.elka.pw.pik.shop.dto;

import pl.elka.pw.pik.shop.domain.model.orders.PaymentType;

public class PaymentTypeData {
    private PaymentType id;
    private String name;

    public PaymentTypeData() {}

    public PaymentTypeData(PaymentType paymentType) {
        if (paymentType != null) {
            this.id = paymentType;
            this.name = paymentType.getDisplayText();
        }
    }

    public PaymentType getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
