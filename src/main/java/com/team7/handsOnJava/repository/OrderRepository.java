package com.team7.handsOnJava.repository;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.Customer;
import com.team7.handsOnJava.model.Order;

import com.team7.handsOnJava.model.OrderItem;
import com.team7.handsOnJava.model.Product;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Slf4j
public class OrderRepository implements CRUDRepository<Order, String>{

    public Map<Long, ArrayList<Long>> findTotNumAndCostOfPurchasesProduct(Product product) throws EshopException {
        try(Connection connection = DataSource.getConnection();
            PreparedStatement preparedstatement = connection.prepareStatement(
                    SqlCommandRepository.get("select.report.product.000"))) {

            log.debug("Finding total number and cost of purchases for a particular product with product ID={}",product.getId());

            preparedstatement.setString(1, product.getId());

            ResultSet resultSet = preparedstatement.executeQuery();
            final HashMap<Long, ArrayList<Long>> totNumAndCostOfPurchasesProduct = new HashMap<>();
            while (resultSet.next()) {
                ArrayList<Long> list = new ArrayList<>();
                list.add(resultSet.getLong("total_quantity"));
                list.add(resultSet.getLong("total_price"));
                totNumAndCostOfPurchasesProduct.put(resultSet.getLong("product_id"),list);
                list.remove(0);
                list.remove(1);
            }
            //log.debug("Found most popular departments based on enrollment {}", mostPopularDepartments);
            return totNumAndCostOfPurchasesProduct;

        } catch (SQLException e) {
            throw new EshopException("Could not find total number or cost of purchases for product",e);
        }
    }
    @Override
    public List<Order> findAll() throws EshopException {
        return null;
    }

    @Override
    public Optional<Order> findByID(String s) throws EshopException {
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
            generatedKeys.next(); // we only suppose that there is a single generated key
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

            log.debug("Creating order ietem with ID {}", orderItem.getId());

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

}
