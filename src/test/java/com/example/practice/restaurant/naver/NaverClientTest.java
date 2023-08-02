package com.example.practice.restaurant.naver;

import com.example.practice.restaurant.naver.dto.SearchImageReq;
import com.example.practice.restaurant.naver.dto.SearchLocalReq;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NaverClientTest {

    @Autowired
    private NaverClient naverClient;

    @Test
    void searchLocalTest() {
        var search = new SearchLocalReq();
        search.setQuery("갈비");

        var result = naverClient.searchLocal(search);
        System.out.println(result);
    }

    @Test
    void searchImageTest() {
        var search = new SearchImageReq();
        search.setQuery("갈비");

        var result = naverClient.searchImage(search);
        System.out.println(result);
    }
}