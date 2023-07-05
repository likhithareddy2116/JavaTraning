package com.sample.rgt.Main;

import java.util.List;
import java.util.Scanner;

import com.sample.rgt.Entity.AutomaticBiddingstratagey;
import com.sample.rgt.Entity.Bid;
import com.sample.rgt.Entity.IncrementBiddingStrategy;
import com.sample.rgt.Entity.Item;
import com.sample.rgt.Entity.User;
import com.sample.rgt.Interface.BiddingStrategy;
import com.sample.rgt.service.ItemManagementService;
import com.sample.rgt.service.UserManagementService;

public class OnlineBiddingSystem {
	private static final Scanner scanner = new Scanner(System.in);
	private UserManagementService userManagementService;
	private ItemManagementService itemManagementService;
	private User currentUser;

	public OnlineBiddingSystem() {
		this.userManagementService = new UserManagementService();
		this.itemManagementService = new ItemManagementService();
		this.currentUser = null;
	}

	public void start() {
		while (true) {
			displayMainMenu();
			int option = scanner.nextInt();
			switch (option) {
			case 1:
				login();
				break;
			case 2:
				createAccount();
				break;
			case 3:
				System.out.println("Goodbye!");
				return;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
	}

	private void displayMainMenu() {
		System.out.println("Welcome to the Online Bidding System!");
		System.out.println("1. Login");
		System.out.println("2. Create Account");
		System.out.println("3. Exit");
		System.out.print("Choose an option: ");
	}

	private void displayUserMenu() {
		System.out.println("Welcome to the Online Bidding System!");
		System.out.println("1. Add Item");
		System.out.println("2. Search Items");
		System.out.println("3. View Bidding History");
		System.out.println("4. Logout");
		System.out.print("Choose an option: ");
	}

	private void login() {
		System.out.print("Enter username: ");
		String username = scanner.next();
		System.out.print("Enter password: ");
		String password = scanner.next();

		currentUser = userManagementService.authenticateUser(username, password);
		if (currentUser != null) {
			System.out.println("Login successful!");
			while (true) {
				displayUserMenu();
				int option = scanner.nextInt();
				switch (option) {
				case 1:
					addItem();
					break;
				case 2:
					searchItems();
					break;
				case 3:
					viewBiddingHistory();
					break;
				case 4:
					System.out.println("Goodbye!");
					currentUser = null;
					return;
				default:
					System.out.println("Invalid option. Please try again.");
				}
			}
		} else {
			System.out.println("Invalid credentials. Please try again.");
		}
	}

	private void createAccount() {
		System.out.print("Enter username: ");
		String username = scanner.next();
		System.out.print("Enter password: ");
		String password = scanner.next();
		userManagementService.createUser(username, password);
		System.out.println("Account Created successfully");
	}

	private void addItem() {
		System.out.print("Enter item name :");
		String name = scanner.next();
		System.out.print("Enter item description:");
		String desc = scanner.next();
		System.out.print("Enter startingBid:");
		double startingBid = scanner.nextDouble();
		itemManagementService.addItem(name, desc, startingBid);
		System.out.println("Item Added sucessfully");

	}

	private void searchItems() {
		System.out.print("Enter a search keyword: ");
		String keyword = scanner.next();

		List<Item> searchResults = itemManagementService.searchItems(keyword);
		if (searchResults.isEmpty()) {
			System.out.println("No items found matching the search keyword.");
		} else {
			System.out.println("Search results:");
			int index = 1;
			for (Item item : searchResults) {
				System.out.println(index + ". " + item.getName() + " - " + item.getDescription()
						+ " - Current highest bid: " + item.getCurrentHighestBid());
				index++;
			}

			System.out.print("\nEnter the index of the item to place a bid (or 0 to go back): ");
			int selectedIndex = scanner.nextInt();

			if (selectedIndex >= 1 && selectedIndex <= searchResults.size()) {
				Item selected = searchResults.get(selectedIndex - 1);
				System.out.print("Enter a bid amount: ");
				double bidAmount = scanner.nextDouble();

				System.out.println("Select a bidding strategy:");
				System.out.println("1. Incremental Bidding");
				System.out.println("2. Automatic Bidding");
				System.out.print("Choose a bidding strategy: ");
				int strategyChoice = scanner.nextInt();

				BiddingStrategy strategy;
				switch (strategyChoice) {
				case 1:
					strategy = new IncrementBiddingStrategy();
					break;
				case 2:
					strategy = new AutomaticBiddingstratagey();
					break;
				default:
					System.out.println("Invalid choice. Returning to main menu.");
					return;
				}

				User currentUser = userManagementService.getCurrentUser();
				double newBid = strategy.bid(selected, currentUser, bidAmount);
				System.out.println("\nBid placed successfully. Your bid amount: " + newBid);
			}

		}
	}

	private void viewBiddingHistory() {
		User currentUser = userManagementService.getCurrentUser();
		if (currentUser != null) {
			List<Bid> biddingHistory = currentUser.getBiddingHistory();
			if (biddingHistory.isEmpty()) {
				System.out.println("Your bidding history is empty.");
			} else {
				System.out.println("Your bidding history:");
				for (Bid bid : biddingHistory) {
					System.out.println(bid.getItem().getName() + " - " + bid.getItem().getDescription()
							+ " - Bid amount: " + bid.getAmount() + " - Winning bid: " + bid.isWinningBid());
				}
			}
		} else {
			System.out.println("You are not logged in. Please log in to view your bidding history.");
		}
	}

	public static void main(String[] args) {
		OnlineBiddingSystem onlineBiddingSystem = new OnlineBiddingSystem();
		onlineBiddingSystem.start();

	}
}