package com.ms_spring_brgy.payment.services;

import com.ms_spring_brgy.payment.dto.Product_Request_DTO;
import com.ms_spring_brgy.payment.dto.Product_Response_DTO;
import com.ms_spring_brgy.payment.helper.Mapper;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.PriceCollection;
import com.stripe.model.Product;
import com.stripe.model.ProductCollection;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.PriceListParams;
import com.stripe.param.ProductCreateParams;
import com.stripe.param.ProductListParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class Product_Service {
    private final Mapper mapper;

    @Value(value = "${stripe.secret-key}")
    String STRIPE_SECRET_KEY;

    //create product method
    public Map<String, String> createProduct(Product_Request_DTO body) throws StripeException {
        Stripe.apiKey = STRIPE_SECRET_KEY;
        Long computedUnitAmount = body.price() * 100L;

        //create product params
        ProductCreateParams productCreateParams = ProductCreateParams
                .builder()
                .setName(body.name())
                .setDescription(body.description())
                .build();

        //create product
        Product product = Product
                .create(productCreateParams);

        //creating price params
        PriceCreateParams priceCreateParams = PriceCreateParams
                .builder()
                .setProduct(product.getId())
                .setCurrency("php")
                .setUnitAmount(computedUnitAmount)
                .build();

        //create price
        Price price = Price.create(priceCreateParams);

        //set return message
        Map<String, String> message = new HashMap<>();
        message.put("message", "Product Successfully created ID:: " + product.getId());

        return message;
    }

    //fetch products method
    public List<Product_Response_DTO> fetchProducts() throws StripeException {
        Stripe.apiKey = STRIPE_SECRET_KEY;

        //product params
        ProductListParams productListParams = ProductListParams
                .builder()
                .setLimit(5L)
                .build();

        //price params
        PriceListParams priceListParams = PriceListParams
                .builder()
                .setLimit(5L)
                .build();

        //fetch products
        ProductCollection productCollection =  Product
                .list(productListParams);

        //fetch prices
        PriceCollection priceCollection = Price
                .list(priceListParams);

        return mapper
                .productResponse(productCollection, priceCollection);
    }
}
