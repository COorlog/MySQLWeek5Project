package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import dao.RestaurantDao;
import entity.Restaurant;

public class menu {
	
	private RestaurantDao restaurantDao = new RestaurantDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList(
			"Display restaurants",
			"Display restaurant",
			"Create restaurant",
			"Update restaurant",
			"Delete restaurant",
			"Good bye");
	
	public void start() {
		String selection = "";
		
		do {
			printMenu();
			selection = scanner.nextLine();
			
			try {
				if(selection.equals("1")) {
					displayRestaurants();
				}else if (selection.equals("2")) {
					displayRestaurant();
				}else if (selection.equals("3")) {
					createRestaurant();
				}else if (selection.equals("4")) {
					updateRestaurant();
				}else if (selection.equals("5")) {
					deleteRestaurant();
				}else if (selection.equals("6")) {
					end();
				}		

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(!selection.equals("6")) {
				System.out.println("Press enter to continue...");
				scanner.nextLine();
			}
			
			
		}while(!selection.equals("6"));
	}

	private void printMenu() {
		System.out.println("Select an Option:\n-----------------------------");
		for (int i = 0; i < options.size(); i++) {
			System.out.println(i + 1 + ") " + options.get(i));
		}
	}
	
		private void displayRestaurants() throws SQLException {
			List<Restaurant> restaurants = restaurantDao.getAllRestaurants();
			for (Restaurant restaurant : restaurants) {
				System.out.println(restaurant.getName() + ": " + restaurant.getId() );
			}
		}
		
		private void displayRestaurant() throws SQLException {
			System.out.print("Enter restaurant name: ");
			String name = scanner.nextLine();
			Restaurant restaurant = restaurantDao.getRestaurantName(name);
			System.out.println(restaurant.getName() + ": " + restaurant.getId());
		}
		
		private void createRestaurant() throws SQLException {
			System.out.print("Enter new restaurant name:");
			String name = scanner.nextLine();
			System.out.println("Enter new restaurant id:");
			int id = Integer.parseInt(scanner.nextLine());
			restaurantDao.createRestaurant(name, id);
			}
		
		private void updateRestaurant() throws SQLException {
			System.out.print("Enter restaurant name you wish to update:");
			String name = scanner.nextLine();
			System.out.print("Enter new id you wish to update:");
			int id = Integer.parseInt(scanner.nextLine());
			restaurantDao.updateRestaurant(name, id);
			}
		
		private void deleteRestaurant() throws SQLException {
			System.out.print("Enter restaurant name or id you wish to delete:");
			String name = scanner.nextLine();
			restaurantDao.deleteRestaurant(name);
		}
		
		private void end() {
			System.out.println("Goodbye!");
			scanner.close();
			restaurantDao.close();
		}
	}
