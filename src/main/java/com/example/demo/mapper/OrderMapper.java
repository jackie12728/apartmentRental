package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.OrderDto;
import com.example.demo.model.entity.Order;

@Component // 此元件由 Springboot 自動掃描後管理
public class OrderMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public OrderDto toDto(Order order) {
		// Entity 轉 DTO
		return modelMapper.map(order, OrderDto.class);
	}
	
	public Order toEntity(OrderDto orderDto) {
		// DTO 轉 Entity
		return modelMapper.map(orderDto, Order.class);
	}
}
