package fr.eql.al35.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PurchaseOrderDTO  {

    private  String reference;
    private  LocalDateTime creationDate;
    private  LocalDateTime shippingDate;
    private  LocalDateTime shippingRef;
    private  LocalDateTime deliveryDate;
    private  LocalDateTime cancelDate;
    private  Double taxInPrice;
    private  Double taxOutPrice;
    private  Integer userId;
    private  Integer deliveryAddress;
    private  Integer billingAddress;
    private  Integer uuid;
    private Status status;
    private Vat vat;

    private  List<OrderLineDTO> orderLines;

}
