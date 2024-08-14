package org.example.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOrder")
    private int idOrder;
    @Column(name = "orderDate")
    private LocalDate orderDate;
    @Column(name = "customerName")
    private String customerName;
    @Column(name = "customerAddress")
    private String customerAddress;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetailList = new ArrayList<>();

    public Order(LocalDate orderDate, String customerName, String customerAddress) {
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
    }

    public void addOrderDetail(OrderDetail orderDetail){
        orderDetailList.add(orderDetail);
        orderDetail.setOrder(this);
    }

    public void removeOrderDetail(OrderDetail orderDetail){
        orderDetailList.remove(orderDetail);
        orderDetail.setOrder(null);
    }
}
