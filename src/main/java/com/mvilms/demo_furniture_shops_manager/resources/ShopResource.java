package com.mvilms.demo_furniture_shops_manager.resources;

import com.mvilms.demo_furniture_shops_manager.model.Shop;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

@Relation(value="shop", collectionRelation="shops")
public class ShopResource extends ResourceSupport {
    @Getter
    private String name;
    @Getter
    private String city;
    @Getter
    private String state;
    @Getter
    private String address;
    @Getter
    private String phone;

    public ShopResource(Shop shop) {
        this.name = shop.getName();
        this.city = shop.getCity();
        this.state = shop.getState();
        this.address = shop.getAddress();
        this.phone = shop.getPhone();
    }
}
