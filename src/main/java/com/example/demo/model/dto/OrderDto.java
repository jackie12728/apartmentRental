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

	@NotNull(message = "{orderDto.id.notNull}")
	private Long id; // 訂單編號

	@NotNull(message = "{orderDto.listingId.notNull}")
    private Long listingId; // 房源ID

	@NotNull(message = "{orderDto.userId.notNull}")
    private Long userId; // 租客ID

	@NotNull(message = "{orderDto.orderDate.notNull}")
    private LocalDateTime orderDate; // 訂單日期時間

	@NotNull(message = "{orderDto.paymentId.notNull}")
    private Integer paymentId; // 訂單狀態 (1 未付款、2 已付款、0 已取消)

	@NotNull(message = "{orderDto.amount.notNull}")
	@Range(min = 1, message = "{orderDto.amount.range}")
    private String amount; // 訂單金額
}
