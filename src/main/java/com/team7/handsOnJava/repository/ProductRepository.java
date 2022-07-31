package com.team7.handsOnJava.repository;

import com.team7.handsOnJava.exception.EshopException;

import java.sql.Date;
import java.util.ArrayList;

import com.team7.handsOnJava.model.Order;
import lombok.extern.slf4j.Slf4j;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import com.team7.handsOnJava.model.Product;
import static com.team7.handsOnJava.model.Product.*;

@Slf4j

public class ProductRepository implements CRUDRepository<Product> {
    @Override
    public List<Product> findAll() {

        try (Connection connection = HikariConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SqlCommands.get(""))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                Product product = new Product("", "", null);

                product.setProductName(resultSet.getString("name"));
                product.setProductPrice(resultSet.getBigDecimal("price"));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            log.debug("",e);
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Product> findByID(String aString) {

        try (Connection connection = HikariConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SqlCommands.get(""))) {
            preparedStatement.setString(1, aString);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.getString("id").isEmpty()) {
                return Optional.empty();
            } else {
                return Optional.of(builder().id(resultSet.getString("ID")).build());
            }

        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Product product) throws EshopException {
        try (Connection connection = HikariConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SqlCommands.get("delete.table.product.000"))) {

            preparedStatement.setString(1, product.getProductId());
            preparedStatement.setBigDecimal(2, product.getProductPrice());

            int rowAffected = preparedStatement.executeUpdate();
            log.trace("{}} unit {}.", rowAffected == 1 ? "Deleted" : "Failed to delete", product);
        } catch (SQLException e) {
            throw new EshopException("Could not delete product.", e);
        }
    }

    @Override
    public void deleteByID(String id) throws EshopException {

    }

    @Override
    public Product create(Product product) throws EshopException {
        try (Connection connection = HikariConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SqlCommands.get("insert.table.product.000"), new String[]{"id"})) {

            log.debug("Creating product.");

            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setBigDecimal(2, product.getProductPrice());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            product.setId(String.valueOf(generatedKeys.getLong(1)));

            return product;
        } catch (SQLException e) {
            throw new EshopException("Unable to create product.", e);
        }
    }

    @Override
    public List<Product> createAll(Product... products) throws EshopException {
        ArrayList<Product> prod = new ArrayList<>();
        try (Connection connection = HikariConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SqlCommands.get("insert.table.product.000"), new String[]{"id"})) {

            for (Product product : products) {

                preparedStatement.setString(1, product.getProductName());
                preparedStatement.setBigDecimal(2, product.getProductPrice());

                preparedStatement.executeUpdate();

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                generatedKeys.next();
                product.setProductId(generatedKeys.getString(1));
                prod.add(product);
            }
            return prod;
        } catch (SQLException e) {
            log.debug("SQL error", e);
            return null;
        }
    }

    @Override
    public boolean exists(Product entity) {
        return false;
    }

    public void update(Product product) throws EshopException {
        try (Connection connection = HikariConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SqlCommands.get("update.table.product.000"))) {

            log.debug("Updating product with ID={}", product.getId());

            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2,String.valueOf(product.getProductPrice()));
            preparedStatement.setLong(3, Long.parseLong(product.getId()));

        } catch (SQLException e) {
            throw new EshopException("Could not update student", e);
        }
        }

    public Map<String, ArrayList<String>> findTotNumAndCostOfPurchasesProduct(Product product) throws EshopException {
        try(Connection connection = HikariConnection.getConnection();
            PreparedStatement preparedstatement = connection.prepareStatement(
                    SqlCommands.get("select.report.product.000"))) {

            log.debug("Finding total number and cost of purchases for a particular product with product ID={}",product.getId());

            preparedstatement.setString(1, product.getId());

            ResultSet resultSet = preparedstatement.executeQuery();
            final HashMap<String, ArrayList<String>> totNumAndCostOfPurchasesProduct = new HashMap<>();
            ArrayList<String> list = new ArrayList<>();
            list.add(resultSet.getString("total_quantity"));
            list.add(resultSet.getString("total_price"));
            totNumAndCostOfPurchasesProduct.put(resultSet.getString("product_id"),list);

            return totNumAndCostOfPurchasesProduct;

        } catch (SQLException e) {
            throw new EshopException("Could not find total number or cost of purchases for product",e);
        }
    }
    }








