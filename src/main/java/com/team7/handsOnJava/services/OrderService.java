package com.team7.handsOnJava.services;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.*;
import com.team7.handsOnJava.repository.CRUDRepository;
import com.team7.handsOnJava.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
public class OrderService implements CRUDRepository<Order, String> {

    private OrderRepository orderRepository;
    @Override
    public List<Order> findAll() throws EshopException {
        return null;
    }

    @Override
    public Optional<Order> findByID(String s) throws EshopException {
        return Optional.empty();
    }

    @Override
    public boolean delete(Order order) throws EshopException {
        return false;
    }

    @Override
    public Order create(Order order) throws EshopException {
        log.info("Creating Order");
        Order createdOrder = orderRepository.create(order);
        return createdOrder;
    }

    @Override
    public List<Order> createAll(Order... orders) throws EshopException {
        return null;
    }

    @Override
    public boolean update(Order order) throws EshopException {
        return false;
    }

    private BigDecimal DiscountPaymentMethod(OrderItem orderItem) throws EshopException {
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
    private BigDecimal DiscountTypeOfCustomer(OrderItem orderItem) throws EshopException {
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
        return product.getProductPrice()
                .subtract(product.getProductPrice().multiply((DiscountPaymentMethod(orderItem).add(DiscountTypeOfCustomer(orderItem)))));
    }
}
