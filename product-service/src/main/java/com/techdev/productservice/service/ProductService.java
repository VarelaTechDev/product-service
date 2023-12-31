package com.techdev.productservice.service;

import com.techdev.productservice.dto.ProductRequest;
import com.techdev.productservice.dto.ProductResponse;
import com.techdev.productservice.model.Product;
import com.techdev.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    // @RequiredArgsConstructor - Does this for you!
    // public ProductService(ProductRepository productRepository) {
    //     this.productRepository = productRepository;
    // }


    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        //log.info("Product " + product.getId() + " is saved");
        log.info("Product {} is saved", product.getId());
    }



    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(product -> mapToProductResponse(product)).toList();
        // Alternative
        //products.stream().map(this::mapToProductResponse);

    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
