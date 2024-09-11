package com.goldmarket.resource_server.order.domain;

import com.goldmarket.resource_server.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "order_invoices")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId; // 주문 일렬번호

    @Column(nullable = false)
    private OrderType orderType; // 주문유형

    @Column(nullable = false)
    private String orderNumber; //주문번호

    @CreatedDate
    private LocalDateTime createdAt; // 주문일자

    @OneToMany(mappedBy = "order")
    private List<Product> products = new ArrayList<>(); // 주문한 상품들

    @Column(nullable = false, length = 36)
    private UUID memberId; // 주문자

    @Column(nullable = false)
    private String shippingAddress; // 배송지

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private OrderStatus orderStatus; // 주문상태
}
