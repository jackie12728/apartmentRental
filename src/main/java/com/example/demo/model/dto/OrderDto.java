package com.example.demo.model.dto;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

	@NotNull(message = "{orderDto.orderId.notNull}")
	private Long orderId; // 訂單編號

	@NotNull(message = "{orderDto.orderListingId.notNull}")
    private Long orderListingId; // 房源ID

	@NotNull(message = "{orderDto.orderUserId.notNull}")
    private Long orderUserId; // 租客ID

	@NotNull(message = "{orderDto.orderDate.notNull}")
    private LocalDateTime orderDate; // 訂單日期時間

	@NotNull(message = "{orderDto.orderStatus.notNull}")
    private Integer orderStatus; // 訂單狀態 (1 未付款、2 已付款、0 已取消)

	@NotNull(message = "{orderDto.amount.notNull}")
	@Range(min = 1, message = "{orderDto.amount.range}")
    private String amount; // 訂單金額
}
