package com.spring.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.exception.InvalidCredentialsException;
import com.spring.model.Product;
import com.spring.model.User;

@Repository
public class CustomerRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public User verifyLogin(String username, String password) throws InvalidCredentialsException {
		String sql = "SELECT * FROM user WHERE username=? AND password=?";

		PreparedStatementCreator psc = con -> {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			return pstmt;
		};

		List<User> users = jdbcTemplate.query(psc, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
				return user;
			}
		});

		if (users.isEmpty()) {
			throw new InvalidCredentialsException("Invalid Credentials");
		}
		return users.get(0);
	}

	public boolean isProductAvailable(int productId) {
		String sql = "SELECT COUNT(*) FROM products WHERE id = ? AND stock > 0";
		Integer count = jdbcTemplate.queryForObject(sql, new Object[] { productId }, Integer.class);
		return count != null && count > 0;
	}

	public void processPurchase(int productId, String username) {
		// Reduce stock of product by 1
		String updateStockSql = "UPDATE products SET stock = stock - 1 WHERE id = ?";
		jdbcTemplate.update(updateStockSql, productId);

		// Insert purchase record
		String insertPurchaseSql = "INSERT INTO purchases (username, product_id) VALUES (?, ?)";
		jdbcTemplate.update(insertPurchaseSql, username, productId);
	}

	public List<Product> findPurchasedProductsByUsername(String username) {
		String sql = "SELECT p.id, p.name, p.price, p.description FROM purchases pr "
				+ "JOIN product p ON pr.product_id = p.id " + "JOIN user u ON pr.user_id = u.id "
				+ "WHERE u.username = ?";
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, username);
				return pstmt;
			}
		};
		return jdbcTemplate.query(psc, (rs, rowNum) -> {
			Product product = new Product();
			product.setId(rs.getInt("id"));
			product.setName(rs.getString("name"));
			product.setPrice(rs.getDouble("price"));
			return product;
		});
	}
}
