package com.team7.handsOnJava.service;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.*;
import com.team7.handsOnJava.repository.CRUDRepository;
import com.team7.handsOnJava.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {this.orderRepository = orderRepository;}

    @Override
    public CRUDRepository<Order> getRepository() {return orderRepository;}

    @Override
    public List<Order> findAll() {
        log.debug("Finding all orders.");
        return super.findAll();
    }

    @Override
    public Order create(Order order) throws EshopException {
        if (order.getStatus() == "APPROVED") {
            log.info("Approving order.");
            return super.create(order);
        }
        throw new EshopException("Order rejected.");
    }

    @Override
    public List<Order> createAll(Order... orders) throws EshopException {
        return super.createAll();
    }

    @Override
    public void delete(Order order) throws EshopException {
        log.info("Deleting order.");
        try {
            super.delete(order);
        } catch (EshopException e) {
            throw new EshopException("Unable to delete order.", e);
        }
    }

    @Override
    public void deleteById(String id) throws EshopException {
        super.deleteById(id);
    }
    @Override
    public List<Order> deleteOrderBeforeCheckOut(String orderID, List<Order> orders) throws EshopException {
        log.info("Deleting order.");
        for (int i=0;i<orders.size();i++) {
            if (orderID == orders.get(i).getId()) {
                checkIfShipped(orders.get(i));
                log.info("Deleting order item with ID = {}",orderID);
                orders.remove(i);
                return orders;
            }
        }
        throw new EshopException("Unable to find order item in the provided order");
    }

    @Override
    public boolean exists(Order entity) throws EshopException {
        return false;
    }

    @Override
    public List<OrderItem> deleteOrderItem(List<OrderItem> orderItems,OrderItem orderItem) throws EshopException {
        checkIfShipped(orderItem.getOrder());
        log.info("Deleting order item.");
        orderItems.remove(orderItem);
        return orderItems;
    }
    @Override
    public boolean isOrderItemOnList(List<OrderItem> orderItems,OrderItem orderItem) {
        for (OrderItem o : orderItems) {
            if (o.getId() == orderItem.getId()) {
                return true;
            }
        }
        return false;
    }
    @Override
    public List<OrderItem> deleteOrderItembyID(List<OrderItem> orderItems,String orderItemID) throws EshopException {
        for (int i=0;i<orderItems.size();i++) {
            if (orderItemID == orderItems.get(i).getId()) {
                checkIfShipped(orderItems.get(i).getOrder());
                log.info("Deleting order item with ID = {}",orderItemID);
                orderItems.remove(i);
                return orderItems;
            }
        }
        throw new EshopException("Unable to find order item in the provided order");
    }


    @Override
    public OrderItem createOrderItem(OrderItem orderItem) throws EshopException {
        if (orderItem.getOrder().getStatus() == "APPROVED") {
            log.info("Approving order item.");
            return orderRepository.createOrderItem(orderItem);
        }
        throw new EshopException("Order rejected.");
    }


    @Override
    public List<Order> createAll(List<Order> entities) throws EshopException {
        return null;
    }

    public void checkIfShipped(Order order) throws EshopException {
        if (order.getStatus() == "APPROVED") {
            throw new EshopException("Unable to change order. Items already shipped. Please contact costumer support.");
        }
    }
    @Override
    public Long IncreaseOrDecreaseItemQuantity(OrderItem orderItem, Long quantity) throws EshopException {
        try {
            checkIfShipped(orderItem.getOrder());
            if (orderItem.getQuantity() + quantity > 0) {
                log.info("Updating order item quantity");
                return orderItem.getQuantity() + quantity;
            }
            throw new EshopException("Quantity of the item needs to be a positive number.");
        } catch (IllegalArgumentException e) {
            throw new EshopException("The input quantity is invalid. It must be an integer.",e);
        }
    }
    @Override
    public BigDecimal DiscountPaymentMethod(Order order) {
        BigDecimal discountPayment = BigDecimal.valueOf(0);
        switch (order.getChosenPaymentMethod()) {
            case "wireTransfer":
                log.info("Payment with wire transfer.");
                discountPayment = order.getCustomer().getCustomerPaymentMethod().getWireTransfer().getDiscount();
                break;
            case "creditCard":
                log.info("Payment using debit credit card.");
                discountPayment = order.getCustomer().getCustomerPaymentMethod().getCreditDebitCard().getDiscount();
                break;
            case "cash":
                log.info("Payment with cash.");
                discountPayment = order.getCustomer().getCustomerPaymentMethod().getCash().getDiscount();
                break;
        }
        return discountPayment;
    }
    @Override
    public BigDecimal DiscountTypeOfCustomer(Order order) {
        if (order.getCustomer().getTypeOfCustomer() instanceof B2cIndividual) {
            return BigDecimal.valueOf(0);
        } else if (order.getCustomer().getTypeOfCustomer() instanceof B2bBusiness) {
            return BigDecimal.valueOf(0.2);
        } else {
            return BigDecimal.valueOf(0.5);
        }
    }
    @Override
    public BigDecimal FinalPriceOfOrderItem(Order order,Product product)  {
        return product.getProductPrice()
                .subtract(product.getProductPrice().multiply((DiscountPaymentMethod(order).add(DiscountTypeOfCustomer(order)))));
    }

}