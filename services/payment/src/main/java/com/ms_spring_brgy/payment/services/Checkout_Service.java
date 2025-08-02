package com.ms_spring_brgy.payment.services;

import com.ms_spring_brgy.payment.dto.Checkout_Request_DTO;
import com.ms_spring_brgy.payment.helper.Mapper;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.PriceCollection;
import com.stripe.model.PriceSearchResult;
import com.stripe.model.Product;
import com.stripe.model.checkout.Session;
import com.stripe.param.PriceListParams;
import com.stripe.param.PriceSearchParams;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class Checkout_Service {
    private final Mapper mapper;

    @Value(value = "${stripe.secret-key}")
    String STRIPE_SECRET_KEY;

    //accept payment method
    public Map<String, String> acceptPayment(Checkout_Request_DTO checkout) throws StripeException {
        Stripe.apiKey = STRIPE_SECRET_KEY;

        //retrieve a product
        Product product = Product
                .retrieve(checkout.productId());

        //list price params
        PriceListParams priceListParams = PriceListParams
                .builder()
                .setProduct(checkout.productId())
                .build();

        //price collection or list
        PriceCollection priceCollection = Price
                .list(priceListParams);

        //get price object make it optional in case there is no match
        Optional<Price> getPriceObject = priceCollection
                .getData()
                .stream()
                .findFirst();

        //fetch the unit amount from the price object
        Long unitAmount = getPriceObject
                .map(Price::getUnitAmount)
                .orElse(0L);

        //create product
        SessionCreateParams.LineItem.PriceData.ProductData productData = SessionCreateParams.LineItem.PriceData.ProductData
                .builder()
                .setName(product.getName())
                .setDescription(product.getDescription())
                .addImage(  "https://i.pinimg.com/736x/7e/d2/77/7ed277c0d2e43077531fbacafe6b7ffc.jpg")
                .build();

        //create product price
        SessionCreateParams.LineItem.PriceData price = SessionCreateParams.LineItem.PriceData
                .builder()
                .setCurrency("php")
                .setUnitAmount(unitAmount)
                .setProductData(productData)
                .build();


        //create product lineItem
        SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem
                .builder()
                .setQuantity(checkout.quantity())
                .setPriceData(price)
                .build();


        SessionCreateParams sessionCreateParams = SessionCreateParams
                .builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:7004/api/v1/product/success")
                .setCancelUrl("http://localhost:7004/api/v1/product/cancel")
                .addLineItem(lineItem)
                .build();


        Session session = Session.create(sessionCreateParams);

        Map<String, String> url = new HashMap<>();
        url.put("url", session.getUrl());
        return url;
    }
}
