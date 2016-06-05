package pl.elka.pw.pik.shop.dto;

/**
 * Created by Igor on 04.06.2016.
 */
public class OrderSearchParams extends PagableSearchParams{
    private String creation_time;
    private Long id;
    private String delivery_street;


    public OrderSearchParams() {
        super(10, 0, null, null);
    }

    public void setCreation_time(String creation_time) {
        this.creation_time= creation_time;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDelivery_streetStreet(String delivery_street) {
        this.delivery_street= delivery_street;
    }

    public String getCreation_time() {
        return creation_time;
    }

    public Long getId() {
        return id;
    }

    public String getDelivery_street() {
        return delivery_street;
    }

}
