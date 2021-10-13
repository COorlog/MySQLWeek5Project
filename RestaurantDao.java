package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Restaurant;

public class RestaurantDao {
	private Connection connection;
	private final String GET_ALL_RESTAURANTS = "SELECT * FROM restaurant";
	private final String GET_RESTAURANT_NAME = "SELECT * FROM restaurant WHERE name = ?";
	private final String CREATE_RESTAURANT = "INSERT INTO restaurant(name, id) VALUES(?,?)";
	private final String UPDATE_RESTAURANT = "UPDATE restaurant SET id = ? WHERE name = ?";
	private final String DELETE_RESTAURANT = "DELETE FROM restaurant WHERE(name)= ?";
	
	public RestaurantDao() {
		connection = DBConnection.getConnection();
		}
	
	public List<Restaurant> getAllRestaurants() throws SQLException {
				
		ResultSet rs= connection.prepareStatement(GET_ALL_RESTAURANTS).executeQuery();
		
		List<Restaurant> Restaurants = new ArrayList<Restaurant>();
				
		while (rs.next()) {
			Restaurants.add( new Restaurant(rs.getString("name"), rs.getInt("id")));
		}
		
		return Restaurants;
	}

	public Restaurant getRestaurantName(String name) throws SQLException  {
		PreparedStatement ps = connection.prepareStatement(GET_RESTAURANT_NAME);
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return new Restaurant(rs.getString("name"), rs.getInt("id"));
	}

		public void createRestaurant(String name, int id) throws SQLException    {
		PreparedStatement ps = connection.prepareStatement(CREATE_RESTAURANT);
		ps.setString(1, name);
		ps.setInt(2, id);
		ps.executeUpdate();
	}

	public void updateRestaurant(String name, int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_RESTAURANT);
		ps.setString(2, name);
		ps.setInt(1, id);
		ps.executeUpdate();
	}

	public void deleteRestaurant(String name) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_RESTAURANT);
		ps.setString(1, name);
		ps.executeUpdate();
	}

	public void close() {
		return;
		
	}
		
}
	