package com.team7.handsOnJava.repository;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.Customer;
import com.team7.handsOnJava.model.Order;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Slf4j
public class OrderRepository implements CRUDRepository<Order, String>{

    public Map<Long, ArrayList<Long>> findTotNumAndCostOfPurchasesProduct() throws EshopException {
        try(Connection connection = DataSource.getConnection();
            PreparedStatement preparedstatement = connection.prepareStatement(
                    SqlCommandRepository.get("select.report.orderitem.000"))) {

            log.debug("Finding total number and cost of purchases for a particular product with order ID={}",);

            ResultSet resultSet = preparedstatement.executeQuery();
            final HashMap<Long, ArrayList<Long>> totNumAndCostOfPurchasesProduct = new HashMap<>();
            while (resultSet.next()) {
                ArrayList<Long> list = new ArrayList<>();
                list.add(resultSet.getLong("total_quantity"));
                list.add(1L);
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
    public boolean delete(Order order) throws EshopException {
        return false;
    }

    @Override
    public Order create(Order order) throws EshopException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SqlCommandRepository.get("insert.table.order.000"), new String[]{"id"})) {

            log.debug("Creating order {}", order);

            preparedStatement.setDate(1, order.getOrderDate());
            preparedStatement.setDate(2, order.getShipmentDate());
            preparedStatement.setString(3, order.getStatus());
            preparedStatement.setString(4, order.getCustomer().getId());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next(); // we only suppose that there is a single generated key
            order.setId(String.valueOf(generatedKeys.getLong(1)));

            return order;
        } catch (SQLException e) {
            throw new EshopException("Unable to create order.", e);
        }
    }

    @Override
    public List<Order> createAll(Order... orders) throws EshopException {
        return null;
    }

    @Override
    public boolean update(Order order) throws EshopException {
        return false;
    }
}
