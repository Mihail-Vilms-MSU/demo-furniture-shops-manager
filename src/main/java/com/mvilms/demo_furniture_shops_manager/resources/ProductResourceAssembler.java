package com.mvilms.demo_furniture_shops_manager.resources;

import com.mvilms.demo_furniture_shops_manager.model.Product;
import com.mvilms.demo_furniture_shops_manager.web.ProductController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

@Service
public class ProductResourceAssembler extends ResourceAssemblerSupport<Product, ProductResource> {
    public ProductResourceAssembler() {
        super(ProductController.class, ProductResource.class);
    }

    @Override
    protected ProductResource instantiateResource(Product product) {
        return new ProductResource(product);
    }
    @Override
    public ProductResource toResource(Product product) {
        return createResourceWithId(product.getId(), product);
    }
}
