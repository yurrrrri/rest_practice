package com.example.practice.restaurant.wishlist.repository;

import com.example.practice.restaurant.wishlist.entity.WishListEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class WishListRepositoryTest {

    @Autowired
    private WishListRepository wishListRepository;

    private WishListEntity create() {
        var wishList = new WishListEntity();
        wishList.setTitle("title");
        wishList.setCategory("category");
        wishList.setAddress("address");
        wishList.setRoadAddress("roadAddress");
        wishList.setHomePageLink("");
        wishList.setImageLink("");
        wishList.setVisit(false);
        wishList.setVisitCount(0);
        wishList.setLastVisitDate(null);

        return wishList;
    }

    @Test
    void save() {
        var wishListEntity = create();
        var expected = wishListRepository.save(wishListEntity);

        assertThat(expected).isNotNull();
        assertThat(expected.getIndex()).isEqualTo(1);
    }

    @Test
    void findById() {
        var wishListEntity = create();
        wishListRepository.save(wishListEntity);
        var expected = wishListRepository.findById(1);

        assertThat(expected).isPresent();
        assertThat(expected.get().getIndex()).isEqualTo(1);
    }

    @Test
    void findAll() {
        var wishListEntity1 = create();
        wishListRepository.save(wishListEntity1);

        var wishListEntity2 = create();
        wishListRepository.save(wishListEntity2);

        assertThat(wishListRepository.findAll()).hasSize(2);
    }

    @Test
    void deleteById() {
        var wishListEntity = create();
        wishListRepository.save(wishListEntity);
        wishListRepository.deleteById(1);

        assertThat(wishListRepository.findAll()).isEmpty();
    }

    @Test
    void updateSave() {
        var wishListEntity = create();
        var expected = wishListRepository.save(wishListEntity);

        expected.setTitle("update test");
        var saveEntity = wishListRepository.save(expected);

        assertThat(saveEntity.getTitle()).isEqualTo("update test");
        assertThat(wishListRepository.findAll()).hasSize(1);
    }

}