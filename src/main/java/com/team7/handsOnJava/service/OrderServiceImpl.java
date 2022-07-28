package com.team7.handsOnJava.service;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.*;
import com.team7.handsOnJava.repository.CRUDRepository;
import com.team7.handsOnJava.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {this.orderRepository = orderRepository;}

    @Override
    public List<Order> findAll() throws EshopException {
        return null;
    }

    /*@Override
    public Optional<Order> findByID(String s) throws EshopException {
        return Optional.empty();
    }
*/
    @Override
    public void delete(Order order) throws EshopException {
        log.info("Deleting order.");
        try {
            orderRepository.delete(order);
        } catch (EshopException e) {
            throw new EshopException("Unable to delete order.", e);
        }
    }

    @Override
    public void deleteById(Long id) throws EshopException {

    }

    @Override
    public boolean exists(Order entity) throws EshopException {
        return false;
    }

    @Override
    public Order get(Long id) throws EshopException {
        return null;
    }

    public List<OrderItem> deleteOrderItem(List<OrderItem> orderItems,OrderItem orderItem) throws EshopException {
        checkIfShipped(orderItem.getOrder());
        log.info("Deleting order item.");
        orderItems.remove(orderItem);
        return orderItems;
    }

    /*
        public boolean deleteOrderItemFromDatabase(List<OrderItem> orderItems,OrderItem orderItem) throws EshopException {
        log.info("Deleting order.");
        return orderRepository.delete(order);
    }

     */

    @Override
    public Order create(Order order) throws EshopException {
        if (order.getStatus() == "APPROVED") {
            log.info("Approving order.");
            return orderRepository.create(order);
        }
        throw new EshopException("Order rejected."); //kapos kati na girizei gia na ginei to status rejected
    }

    public OrderItem createOrderItem(OrderItem orderItem) throws EshopException {
        if (orderItem.getOrder().getStatus() == "APPROVED") {
            log.info("Approving order item.");
            return orderRepository.createOrderItem(orderItem);
        }
        throw new EshopException("Order rejected.");
    }
    @Override
    public List<Order> createAll(Order... orders) throws EshopException {
        return null;
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

    public Long changeOrderItemQuantity(Order order, OrderItem orderItem, Long quantity) throws EshopException {
        try {
            checkIfShipped(order);
            if (orderItem.getQuantity() + quantity > 0) {
                log.info("Updating order item quantity");
                return orderItem.getQuantity() + quantity;
            }
            throw new EshopException("Quantity of the item needs to be a positive number.");
        } catch (IllegalArgumentException e) {
            throw new EshopException("The input quantity is invalid. It must be an integer.",e);
        }
    }

    public BigDecimal DiscountPaymentMethod(OrderItem orderItem) throws EshopException {
        BigDecimal discountPayment;
        switch (orderItem.getOrder().getChosenPaymentMethod()) {
            case "wireTransfer":
                log.info("Payment with wire transfer.");
                discountPayment = orderItem.getOrder().getCustomer().getCustomerPaymentMethod().getWireTransfer().getDiscount();
                break;
            case "creditCard":
                log.info("Payment using debit credit card.");
                discountPayment = orderItem.getOrder().getCustomer().getCustomerPaymentMethod().getCreditDebitCard().getDiscount();
                break;
            case "cash":
                log.info("Payment with cash.");
                discountPayment = orderItem.getOrder().getCustomer().getCustomerPaymentMethod().getCash().getDiscount();
                break;
            default:
                throw new EshopException("Unable to process payment method.");
        }
        return discountPayment;
    }
    public BigDecimal DiscountTypeOfCustomer(OrderItem orderItem) throws EshopException {
        if (orderItem.getOrder().getCustomer().getTypeOfCustomer() instanceof B2cIndividual) {
            return BigDecimal.valueOf(0);
        } else if (orderItem.getOrder().getCustomer().getTypeOfCustomer() instanceof B2bBusiness) {
            return BigDecimal.valueOf(0.2);
        } else if (orderItem.getOrder().getCustomer().getTypeOfCustomer() instanceof B2gGovernment) {
            return BigDecimal.valueOf(0.5);
        }
        throw new EshopException("Invalid type of customer");
    }
    public BigDecimal FinalPriceOfOrderItem(OrderItem orderItem,Product product) throws EshopException {
        checkIfShipped(orderItem.getOrder());
        return product.getProductPrice()
                .subtract(product.getProductPrice().multiply((DiscountPaymentMethod(orderItem).add(DiscountTypeOfCustomer(orderItem)))));
    }
}
