package com.example.demo.service.impl;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.model.entity.Listing;

public class SearchBarSpecifications {

	public static Specification<Listing> byCityId(Long cityId) {
        return (root, query, criteriaBuilder) -> 
            cityId == null ? null : criteriaBuilder.equal(root.get("city").get("id"), cityId);
    }

    public static Specification<Listing> byRegionId(Long regionId) {
        return (root, query, criteriaBuilder) -> 
            regionId == null ? null : criteriaBuilder.equal(root.get("region").get("id"), regionId);
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
