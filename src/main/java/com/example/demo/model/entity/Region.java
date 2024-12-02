package com.example.demo.model.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity // 實體類與資料表對應(會自動建立資料表)
@Table(name = "region")
public class Region {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 區域 ID

    @Column(nullable = false)
    private String name; // 區域名稱
    
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    
    @OneToMany(mappedBy = "region", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Listing> listings;
}
