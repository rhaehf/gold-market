package com.goldmarket.resource_server.product.domain;

import com.goldmarket.resource_server.order.domain.Order;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId; // 상품 일렬번호

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductType productType; // 상품유형

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Item item; // 품목

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal quantity; // 수량

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal price; // 가격

    @CreatedDate
    private LocalDateTime createdAt; // 상품 등록일

    @Column(nullable = false, length = 36)
    private UUID productBy; // 상품 등록인

    @LastModifiedDate
    private LocalDateTime updatedAt; // 상품 수정일

    @Column(nullable = false, columnDefinition = "TEXT")
    private String comment; // 변경 이유

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
}
