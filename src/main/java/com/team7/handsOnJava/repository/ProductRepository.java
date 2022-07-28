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
        return null;
    }

    @Override
    public Optional<Product> findByID(String aString) throws EshopException {
        return Optional.empty();
    }

    @Override
    public boolean delete(Product product) throws EshopException {
        return false;
    }

    @Override
    public Product create(Product product) throws EshopException {
        return null;
    }

    @Override
    public List<Product> createAll(Product... products) throws EshopException {
        return null;
    }

    @Override
    public boolean update(Product product) throws EshopException {
        return false;
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
