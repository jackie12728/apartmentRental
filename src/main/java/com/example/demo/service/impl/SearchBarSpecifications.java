package com.example.demo.service.impl;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.model.entity.Listing;

public class SearchBarSpecifications {

	public static Specification<Listing> byCityId(Long cityId) {
        return (root, query, criteriaBuilder) -> 
            cityId == null ? null : criteriaBuilder.equal(root.get("city").get("id"), cityId);
    }

	public static Specification<Listing> byRegionIds(List<Long> regionIds) {
	    return (root, query, criteriaBuilder) -> {
	        if (regionIds == null || regionIds.isEmpty()) {
	            return null; // regionId 不設限
	        } else if (regionIds.size() == 1) {
	            // 只有一個 regionId
	            return criteriaBuilder.equal(root.get("region").get("id"), regionIds.get(0));
	        } else {
	            // 多個 regionId
	            return root.get("region").get("id").in(regionIds);
	        }
	    };
	}

    public static Specification<Listing> byRentRange(Integer minRent, Integer maxRent) {
        return (root, query, criteriaBuilder) -> {
            if (minRent == null && maxRent == null) {
                return null;
            }
            if (minRent != null && maxRent != null) {
                return criteriaBuilder.between(root.get("rent"), minRent, maxRent);
            }
            if (minRent != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("rent"), minRent);
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("rent"), maxRent);
        };
    }

    public static Specification<Listing> byListingname(String listingname) {
        return (root, query, criteriaBuilder) -> 
            listingname == null || listingname.isEmpty() ? null : 
            criteriaBuilder.like(root.get("listingname"), "%" + listingname + "%");
    }
}
