package com.ms_spring_brgy.payment.helper;

import com.ms_spring_brgy.payment.dto.Product_Response_DTO;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.PriceCollection;
import com.stripe.model.ProductCollection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
public class Mapper {

    public List<Product_Response_DTO> productResponse(ProductCollection body, PriceCollection prices) {
        return body
                .getData()
                .stream()
                .map(product -> {

                    //filter the prices that matches the product id
                    Optional<Price> matchingPrice = prices.getData()
                            .stream()
                            .filter(price -> Objects.equals(price.getProduct(), product.getId()))
                            .findFirst();

                    //fetch the unit amount from the matched price
                    Long priceAmount = matchingPrice.map(Price::getUnitAmount).orElse(0L);

                    //assign data to the product response dto
                    return Product_Response_DTO
                        .builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(priceAmount)
                        .build();
                    }
                )
                .toList();
    }
}
