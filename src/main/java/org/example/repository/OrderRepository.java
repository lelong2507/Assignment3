package org.example.repository;

import org.example.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order,Integer> {
    @Query("SELECT o FROM Order o JOIN o.orderDetailList od WHERE o.orderDate = CURRENT_DATE")
    List<Order> getOrderByCurrentDate();
    // Em kh co gia tri mo tren 1000 het nen de la 200 nha co :))
    @Query("SELECT o FROM Order o JOIN o.orderDetailList od GROUP BY o.idOrder HAVING SUM(od.quantity * od.unitPrice) > 200")
    List<Order> getOrderHaveTotalMoreThan200();
    @Query("SELECT o FROM Order o JOIN o.orderDetailList od WHERE od.productName = 'JavaBook' GROUP BY o.idOrder")
    List<Order> getOrdersWithProductNameJavaBook();



}
