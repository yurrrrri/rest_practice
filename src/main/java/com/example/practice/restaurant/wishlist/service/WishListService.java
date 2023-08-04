package com.example.practice.restaurant.wishlist.service;

import com.example.practice.restaurant.naver.NaverClient;
import com.example.practice.restaurant.naver.dto.SearchImageReq;
import com.example.practice.restaurant.naver.dto.SearchLocalReq;
import com.example.practice.restaurant.wishlist.dto.WishListDto;
import com.example.practice.restaurant.wishlist.entity.WishListEntity;
import com.example.practice.restaurant.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WishListService {

    private final NaverClient naverClient;
    private final WishListRepository wishListRepository;

    public WishListDto search(String query) {
        // Search Local
        var searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);
        var searchLocalRes = naverClient.searchLocal(searchLocalReq);

        if (searchLocalRes.getTotal() > 0) {
            var localItem = searchLocalRes.getItems().stream().findFirst().get();
            var imageQuery = localItem.getTitle().replaceAll("<[^>]*>", "");
            var searchImageReq = new SearchImageReq();
            searchImageReq.setQuery(imageQuery);

            // Search Image
            var searchImageRes = naverClient.searchImage(searchImageReq);

            if (searchImageRes.getTotal() > 0) {
                var imageItem = searchImageRes.getItems().stream().findFirst().get();
                // Returns result
                var result = new WishListDto();
                result.setTitle(localItem.getTitle());
                result.setCategory(localItem.getCategory());
                result.setAddress(localItem.getAddress());
                result.setRoadAddress(localItem.getRoadAddress());
                result.setHomePageLink(localItem.getLink());
                result.setImageLink(imageItem.getLink());

                return result;
            }
        }
        return new WishListDto();
    }

    public WishListDto add(WishListDto dto) {
        var entity = dtoToEntity(dto);
        wishListRepository.save(entity);
        return entityToDto(entity);
    }

    private WishListEntity dtoToEntity(WishListDto dto) {
        var entity = new WishListEntity();
        entity.setIndex(dto.getIndex());
        entity.setTitle(dto.getTitle());
        entity.setCategory(dto.getCategory());
        entity.setAddress(dto.getAddress());
        entity.setRoadAddress(dto.getRoadAddress());
        entity.setHomePageLink(dto.getHomePageLink());
        entity.setImageLink(dto.getImageLink());
        entity.setVisit(dto.isVisit());
        entity.setVisitCount(dto.getVisitCount());
        entity.setLastVisitDate(dto.getLastVisitDate());

        return entity;
    }

    private WishListDto entityToDto(WishListEntity entity) {
        var dto = new WishListDto();
        dto.setIndex(entity.getIndex());
        dto.setTitle(entity.getTitle());
        dto.setCategory(entity.getCategory());
        dto.setAddress(entity.getAddress());
        dto.setRoadAddress(entity.getRoadAddress());
        dto.setHomePageLink(entity.getHomePageLink());
        dto.setImageLink(entity.getImageLink());
        dto.setVisit(entity.isVisit());
        dto.setVisitCount(entity.getVisitCount());
        dto.setLastVisitDate(entity.getLastVisitDate());

        return dto;
    }

    public List<WishListDto> findAll() {
        return wishListRepository.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
}
