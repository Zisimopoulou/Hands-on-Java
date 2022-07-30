package com.team7.handsOnJava.repository;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.Order;

import com.team7.handsOnJava.model.OrderItem;
import com.team7.handsOnJava.model.Product;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Slf4j
public class OrderRepository implements CRUDRepository<Order>{

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Optional<Order> findByID(String s) {
        return Optional.empty();
    }

    @Override
    public void delete(Order order) throws EshopException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SqlCommandRepository.get("delete.table.order.000"))) {

            log.debug("Deleting order with ID = {}", order);

            preparedStatement.setString(1, order.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new EshopException("Unable to delete order.", e);
        }
    }

    @Override
    public void deleteByID(String id) throws EshopException {

    }

    @Override
    public Order create(Order order) throws EshopException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SqlCommandRepository.get("insert.table.order.000"), new String[]{"id"})) {

            log.debug("Creating order {}", order);

            preparedStatement.setString(1, order.getStatus());
            preparedStatement.setString(2, order.getChosenPaymentMethod());
            preparedStatement.setString(3, order.getCustomer().getId());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            order.setId(String.valueOf(generatedKeys.getLong(1)));

            return order;
        } catch (SQLException e) {
            throw new EshopException("Unable to create order.", e);
        }
    }

    public OrderItem createOrderItem(OrderItem orderItem) throws EshopException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SqlCommandRepository.get("insert.table.orderitem.000"), new String[]{"id"})) {

            log.debug("Creating order item with ID {}", orderItem.getId());

            preparedStatement.setString(1, orderItem.getProduct().getId());
            preparedStatement.setLong(2, orderItem.getQuantity());
            preparedStatement.setBigDecimal(3, orderItem.getPrice());
            preparedStatement.setString(4, orderItem.getOrder().getId());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            orderItem.setId(String.valueOf(generatedKeys.getLong(1)));

            return orderItem;
        } catch (SQLException e) {
            throw new EshopException("Unable to create order.", e);
        }
    }
    @Override
    public List<Order> createAll(Order... orders) throws EshopException {
        return null;
    }

    @Override
    public boolean exists(Order entity) {
        return false;
    }

}
