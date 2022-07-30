package com.team7.handsOnJava.repository;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.Customer;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;

@Slf4j
public class CustomerRepository implements CRUDRepository<Customer>{

    public List<Customer> findAll() {
        return null;
    }

    public Optional<Customer> findByID(String s) {
        return Optional.empty();
    }

    public void delete(Customer customer) throws EshopException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SqlCommandRepository.get("delete.table.customer.000"))) {
            log.debug("Deleting customer with ID = {}", customer);
            preparedStatement.setString(1, customer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new EshopException("Unable to delete order.", e);
        }
    }

    @Override
    public void deleteByID(String id) throws EshopException {

    }

    public Customer create(Customer customer) throws EshopException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SqlCommandRepository.get("insert.table.customer.000"), new String[]{"id"})) {
            log.debug("Creating customer {}", customer);
            preparedStatement.setString(1, customer.getCustomerName());
            preparedStatement.setString(2, customer.getCustomerEmail());
            preparedStatement.setObject(3, customer.getCustomerPaymentMethod());
            preparedStatement.setObject(4, customer.getCustomerAddress());
            preparedStatement.setObject(5, customer.getTypeOfCustomer());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            customer.setId(String.valueOf(generatedKeys.getLong(1)));
            return customer;
        } catch (SQLException e) {
            throw new EshopException("Unable to create customer.", e);
        }
    }

    public List<Customer> createAll(Customer... customers) throws EshopException {
        return null;
    }

    @Override
    public boolean exists(Customer entity) {
        return false;
    }

    public static boolean validEmail(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    public Customer update(Customer customer) throws EshopException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SqlCommandRepository.get("update.table.customer.000"))) {
            log.debug("Updating customer {} with customerID={}", customer, customer.getId());
            preparedStatement.setString(1, customer.getCustomerName());
            preparedStatement.setString(2, customer.getCustomerEmail());
            preparedStatement.setObject(3, customer.getCustomerPaymentMethod());
            preparedStatement.setObject(4, customer.getCustomerAddress());
            preparedStatement.setObject(5, customer.getTypeOfCustomer());
            preparedStatement.executeUpdate();
            return customer;
        } catch (SQLException e) {
            throw new EshopException("Unable to update customer.", e);
        }
    }

    public Map<Long, ArrayList<Long>> findTotNumAndCostOfPurchasesPerCustomer() throws EshopException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedstatement = connection.prepareStatement(
                     SqlCommandRepository.get("select.report.customer.001"))) {
            log.debug("Finding total number and cost of purchases per customer." );
            ResultSet resultSet = preparedstatement.executeQuery();
            final HashMap<Long, ArrayList<Long>> totNumAndCostOfPurchasesProduct = new HashMap<>();
            while (resultSet.next()) {
                ArrayList<Long> list = new ArrayList<>();
                list.add(resultSet.getLong("total_quantity"));
                list.add(resultSet.getLong("total_price"));
                totNumAndCostOfPurchasesProduct.put(resultSet.getLong("customer_id"), list);
                list.remove(0);
                list.remove(1);
            }
            return totNumAndCostOfPurchasesProduct;
        } catch (SQLException e) {
            throw new EshopException("Could not find total number or cost of purchases for product", e);
        }
    }
}
