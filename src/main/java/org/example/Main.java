package org.example;

import org.example.config.JPAConfig;
import org.example.entity.Order;
import org.example.entity.OrderDetail;
import org.example.repository.OrderRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JPAConfig.class);
    static OrderRepository orderRepository = (OrderRepository) applicationContext.getBean("orderRepository");
    public static void main(String[] args) {
//          createOrder();
//        showList();
//        getOrderAndOrderDetailById(2);
//        getOrderByCurDate();
//        getOrderByAmount();
        getOrderByProductName();
    }

    // Create Order Method
    public static void createOrder(){
        OrderDetail orderDetail1 = new OrderDetail();

        orderDetail1.setQuantity(4);
        orderDetail1.setUnitPrice(100.0);
        orderDetail1.setProductName("JavaBook");


        List<OrderDetail> orderDetails = new ArrayList<>();

        orderDetails.add(orderDetail1);


        Order order = new Order();
        order.setOrderDate(LocalDate.parse("2024-08-14"));
        order.setCustomerName("Le Loc");
        order.setCustomerAddress("DN");
        order.setOrderDetailList(orderDetails);
        orderDetail1.setOrder(order);

        orderRepository.save(order);
    }

    public static void showList() {
        List<Order> orderList = (List<Order>) orderRepository.findAll();

        for (Order order : orderList) {
            System.out.println("Order ID: " + order.getIdOrder());
            System.out.println("Order Date: " + order.getOrderDate());
            System.out.println("Customer Name: " + order.getCustomerName());
            System.out.println("Customer Address: " + order.getCustomerAddress());

            System.out.println("Order Details:");
            for (OrderDetail detail : order.getOrderDetailList()) {
                System.out.println(" - Product Name: " + detail.getProductName());
                System.out.println("   Quantity: " + detail.getQuantity());
                System.out.println("   Unit Price: " + detail.getUnitPrice());
            }
            System.out.println("-------------");
        }
    }

    public static void getOrderAndOrderDetailById(int orderId){
        List<Order> orderList = (List<Order>) orderRepository.findAll();
        for (Order order : orderList) {
            if(orderId == order.getIdOrder()){
                System.out.println("Order ID: " + order.getIdOrder());
                System.out.println("Order Date: " + order.getOrderDate());
                System.out.println("Customer Name: " + order.getCustomerName());
                System.out.println("Customer Address: " + order.getCustomerAddress());

                System.out.println("Order Details:");
                for (OrderDetail detail : order.getOrderDetailList()) {
                    System.out.println(" - Product Name: " + detail.getProductName());
                    System.out.println("   Quantity: " + detail.getQuantity());
                    System.out.println("   Unit Price: " + detail.getUnitPrice());
                }
                System.out.println("-------------");
            } else {
                System.out.println("Can't not found 404");
            }
        }
    }

    public static void getOrderByCurDate(){
        List<Order> orderList = orderRepository.getOrderByCurrentDate();
        for (Order order : orderList) {
            System.out.println("Order ID: " + order.getIdOrder());
            System.out.println("Order Date: " + order.getOrderDate());
            System.out.println("Customer Name: " + order.getCustomerName());
            System.out.println("Customer Address: " + order.getCustomerAddress());

            System.out.println("Order Details:");
            for (OrderDetail detail : order.getOrderDetailList()) {
                System.out.println(" - Product Name: " + detail.getProductName());
                System.out.println("   Quantity: " + detail.getQuantity());
                System.out.println("   Unit Price: " + detail.getUnitPrice());
            }
            System.out.println("-------------");
        }
    }

    public static void getOrderByAmount() {
        List<Order> orderList = orderRepository.getOrderHaveTotalMoreThan200();
        double total = 0;
        for (Order order : orderList) {
            System.out.println("Order ID: " + order.getIdOrder());
            System.out.println("Order Date: " + order.getOrderDate());
            System.out.println("Customer Name: " + order.getCustomerName());
            System.out.println("Customer Address: " + order.getCustomerAddress());

            System.out.println("Order Details:");
            for (OrderDetail detail : order.getOrderDetailList()) {
                System.out.println(" - Product Name: " + detail.getProductName());
                System.out.println("   Quantity: " + detail.getQuantity());
                System.out.println("   Unit Price: " + detail.getUnitPrice());
                total = detail.getQuantity() * detail.getUnitPrice();
                System.out.println("Total: " + total);
            }
            System.out.println("-------------");
        }
    }

    public static void getOrderByProductName(){
        List<Order> orderList = (List<Order>) orderRepository.getOrdersWithProductNameJavaBook();
        for (Order order : orderList) {
            System.out.println("Order ID: " + order.getIdOrder());
            System.out.println("Order Date: " + order.getOrderDate());
            System.out.println("Customer Name: " + order.getCustomerName());
            System.out.println("Customer Address: " + order.getCustomerAddress());

            System.out.println("Order Details:");
            for (OrderDetail detail : order.getOrderDetailList()) {
                System.out.println(" - Product Name: " + detail.getProductName());
                System.out.println("   Quantity: " + detail.getQuantity());
                System.out.println("   Unit Price: " + detail.getUnitPrice());
            }
            System.out.println("-------------");
        }
    }
}