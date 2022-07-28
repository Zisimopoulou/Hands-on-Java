package com.team7.handsOnJava.repository;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ProductRepository implements CRUDRepository<Product, String>{
    @Override
    public List<Product> findAll() throws EshopException {

        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SqlCommandRepository.get(""))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                Product product = new Product( );

                product.setProductName(resultSet.getString("name"));
                product.setProductPrice(resultSet.getBigDecimal("price"));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            log.debug(e);
            return Collections.emptyList();
        }
    }


    @Override
    public Optional<Product> findByID(String aString) throws EshopException {

        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SqlCommandRepository.get(""))) {
            preparedStatement.setString(1, aString);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.getString("id").isEmpty()) {
                return Optional.empty();
            } else {
                return Optional.of(Product.builder().productID(resultSet.getString("ID")));
            }
            return product;
        } catch (SQLException e) {
            return Optional.empty();
        }
    }



    @Override
    public boolean delete(Product product) throws EshopException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SqlCommandRepository.get("delete.table.product.000"))) {

            preparedStatement.setString(1, product.getProductID());
            preparedStatement.setString(2, product.getProductPrice());

            int rowAffected = preparedStatement.executeUpdate();
            log.trace("{}} unit {}.", rowAffected == 1 ? "Deleted" : "Failed to delete", product);
            return rowAffected == 1;
        } catch (SQLException e) {
            throw new EshopException("Could not delete product.", e);
        }
    }



    @Override
    public Product create(Product product) throws EshopException {
        return null;
    }

    @Override
    public List<Product> createAll(Product... products) throws EshopException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SqlCommandRepository.get("insert.table.product.000"), new String[]{"id"})) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getProductPrice());

            preparedStatement.executeUpdate();

            // setting id
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next(); // we only suppose that there is a single generated key
            product.setProductID(generatedKeys.getString(1));
        } catch (SQLException e) {
            log.debug(e);
            return null;
        }



        return null;
    }

    @Override
    public boolean update(Product product) throws EshopException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SqlCommandRepository.get("delete.table.product.000"))) {

            preparedStatement.setBigDecimal(1, product.getProductPrice());


            int rowAffected = preparedStatement.executeUpdate();
            log.trace("{} product {}.", rowAffected == 1 ? "Deleted" : "Failed to delete", product);
            return rowAffected == 1;
        } catch (SQLException e) {
            throw new EshopException("Could not delete product.", e);
        }
    }





    public Map<Long, ArrayList<Long>> findTotNumAndCostOfPurchasesProduct(Product product) throws EshopException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedstatement = connection.prepareStatement(
                     SqlCommandRepository.get("select.report.orderitem.000"))) {

            log.debug("Finding total number and cost of purchases for a particular product with order ID=?????????" );

            preparedstatement.setString(1,product.getId());

            ResultSet resultSet = preparedstatement.executeQuery();
            final HashMap<Long, ArrayList<Long>> totNumAndCostOfPurchasesProduct = new HashMap<>();
            while (resultSet.next()) {
                ArrayList<Long> list = new ArrayList<>();
                list.add(resultSet.getLong("total_quantity"));
                list.add(resultSet.getLong("total_price"));
                totNumAndCostOfPurchasesProduct.put(resultSet.getLong("product_id"), list);
                list.remove(0);
                list.remove(1);
            }

            return totNumAndCostOfPurchasesProduct;

        } catch (SQLException e) {
            throw new EshopException("Could not find total number or cost of purchases for product", e);
        }
    }
}







