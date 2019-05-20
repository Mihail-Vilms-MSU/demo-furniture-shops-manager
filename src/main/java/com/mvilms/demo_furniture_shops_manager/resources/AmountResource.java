package com.mvilms.demo_furniture_shops_manager.resources;

import com.mvilms.demo_furniture_shops_manager.model.ShopToProduct;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

@Relation(value="amount", collectionRelation="amounts")
public class AmountResource extends ResourceSupport {
    @Getter
    private Long amountId;
    @Getter
    private ShopResource shop;
    @Getter
    private ProductResource product;
    @Getter
    private Long amount;

    private final ShopResourceAssembler shopAssembler = new ShopResourceAssembler();
    private final ProductResourceAssembler productAssembler = new ProductResourceAssembler();

    public AmountResource(ShopToProduct shopToProduct) {
        this.amountId = shopToProduct.getId();
        this.shop = shopAssembler.toResource(shopToProduct.getShop());
        this.product = productAssembler.toResource(shopToProduct.getProduct());
        this.amount = shopToProduct.getAmount();
    }
}
