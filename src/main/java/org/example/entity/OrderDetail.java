package org.example.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table (name = "orderDetail")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idOrderDetail")
    private int idOrderDetail;
    @ManyToOne
    @JoinColumn(name = "idOrder", nullable = false)
    private Order order;
    @Column(name = "productName")
    private String productName;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "unitPrice")
    private double unitPrice;
}
