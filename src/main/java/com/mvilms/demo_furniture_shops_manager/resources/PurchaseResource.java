package com.mvilms.demo_furniture_shops_manager.resources;

import com.mvilms.demo_furniture_shops_manager.model.Purchase;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.math.BigDecimal;
import java.util.Date;

@Relation(value="purchase", collectionRelation="purchases")
public class PurchaseResource extends ResourceSupport {
    @Getter
    private String purchaseId;
    @Getter
    private EmployeeResource employee;
    @Getter
    private ShopResource shop;
    @Getter
    private BigDecimal price;
    @Getter
    private Date createdAt;

    public PurchaseResource(Purchase purchase){
        this.purchaseId = purchase.getId();

        this.shop = new ShopResource(purchase.getShop());
        this.employee = new EmployeeResource(purchase.getEmployee());

        this.price = purchase.getTotalPrice();
        this.createdAt = purchase.getRegisteredAt();
    }
}
