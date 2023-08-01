package com.example.practice.restaurant.wishlist.entity;

import com.example.practice.restaurant.db.MemoryDbEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class WishListEntity extends MemoryDbEntity {

    private String title;
    private String category;
    private String address;
    private String roadAddress;
    private String homePageLink;
    private String imageLink;
    private boolean isVisit;
    private int visitCount;
    private LocalDateTime lastVisitDate;

}
