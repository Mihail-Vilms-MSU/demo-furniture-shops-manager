package com.mvilms.demo_furniture_shops_manager.resources;

import com.mvilms.demo_furniture_shops_manager.model.Product;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.math.BigDecimal;
import java.util.Date;

@Relation(value="product", collectionRelation="products")
public class ProductResource extends ResourceSupport {
    @Getter
    private String productId;
    @Getter
    private String name;
    @Getter
    private BigDecimal price;
    @Getter
    private String type;
    @Getter
    private String description;
    @Getter
    private boolean isActive;
    @Getter
    private Long createdAt;
    @Getter
    private Long updatedAt;

    public ProductResource(Product product) {
        this.productId = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.type = product.getType();
        this.description = product.getDescription();
        this.isActive = product.isActive();
        this.createdAt = product.getCreatedAt().getTime();
        this.updatedAt = product.getUpdatedAt().getTime();
    }
}
