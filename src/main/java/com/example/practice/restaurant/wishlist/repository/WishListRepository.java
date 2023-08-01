package com.example.practice.restaurant.wishlist.repository;

import com.example.practice.restaurant.db.MemoryDbRepositoryAbstract;
import com.example.practice.restaurant.wishlist.entity.WishListEntity;
import org.springframework.stereotype.Repository;

@Repository
public class WishListRepository extends MemoryDbRepositoryAbstract<WishListEntity> {
}
