package pl.elka.pw.pik.shop.domain.model.orders;

public enum PaymentType {
    CASH_ON_DELIVERY("Płatność gotówką"),
    ONLINE_PAYMENT("Płatność online"),
    BANK_TRANSFER("Tradycyjny przelew bankowy");

    private String displayText;

    PaymentType(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}
