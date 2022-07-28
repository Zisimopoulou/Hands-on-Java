package com.team7.handsOnJava.repository;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.Customer;
import lombok.extern.slf4j.Slf4j;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
public class CustomerRepository implements CRUDRepository<Customer, String>{
    @Override
    public List<Customer> findAll() throws EshopException {
        return null;
    }

    @Override
    public Optional<Customer> findByID(String s) throws EshopException {
        return Optional.empty();
    }
    @Override
    public boolean delete(Customer customer) throws EshopException {
        return false;
    }
    @Override
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
            generatedKeys.next(); // we only suppose that there is a single generated key
            customer.setId(String.valueOf(generatedKeys.getLong(1)));
            return customer;
        } catch (SQLException e) {
            throw new EshopException("Unable to create customer.", e);
        }
    }

    @Override
    public List<Customer> createAll(Customer... customers) throws EshopException {
        return null;
    }

    @Override
    public boolean update(Customer customer) throws EshopException {
        return false;
    }
}
