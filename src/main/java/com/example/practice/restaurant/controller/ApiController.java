package com.example.practice.restaurant.controller;

import com.example.practice.restaurant.wishlist.dto.WishListDto;
import com.example.practice.restaurant.wishlist.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/restaurant")
public class ApiController {

    private final WishListService wishListService;

    @GetMapping("/search")
    public WishListDto search(@RequestParam String query) {
        return wishListService.search(query);
    }

    @PostMapping("")
    public WishListDto add(@RequestBody WishListDto dto) {
        return wishListService.add(dto);
    }

    @GetMapping("/all")
    public List<WishListDto> findAll() {
        return wishListService.findAll();
    }

}
