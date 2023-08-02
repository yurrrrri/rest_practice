package com.example.practice.restaurant.wishlist.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class WishListServiceTest {

    @Autowired
    private WishListService wishListService;

    @Test
    void searchTest() {
        var result = wishListService.search("갈비");
        assertThat(result).isNotNull();
    }

}