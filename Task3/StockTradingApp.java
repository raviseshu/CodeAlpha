

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StockTradingApp {
    private static final Map<String, Double> stockPrices = new HashMap<>();
    private static final Map<String, Integer> portfolio = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Initialize stock prices
        stockPrices.put("Company A", 100.0);
        stockPrices.put("Company B", 200.0);
        
        System.out.println("Welcome to the Simulated Stock Trading Platform");
        System.out.println("------------------------------------------------");
        
        while (true) {
            System.out.println("\nMarket Data:");
            for (String stock : stockPrices.keySet()) {
                System.out.printf("%s - $%.2f\n", stock, stockPrices.get(stock));
            }
            
            System.out.println("\nOptions:");
            System.out.println("1. Buy Stock");
            System.out.println("2. View Portfolio");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            
            int choice;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input
                continue; // Restart the loop
            }
            
            switch (choice) {
                case 1:
                    buyStock(scanner);
                    break;
                case 2:
                    viewPortfolio();
                    break;
                case 3:
                    System.out.println("Exiting the application. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void buyStock(Scanner scanner) {
        System.out.print("Enter the stock name you want to buy: ");
        scanner.nextLine(); // Consume the newline character left by nextInt()
        String stockName = scanner.nextLine().trim(); // Read the full line for stock name
        
        if (!stockPrices.containsKey(stockName)) {
            System.out.println("Stock not found.");
            return;
        }
        
        System.out.print("Enter the quantity to buy: ");
        int quantity;
        
        if (scanner.hasNextInt()) {
            quantity = scanner.nextInt();
        } else {
            System.out.println("Invalid quantity. Please enter a number.");
            scanner.next(); // Clear the invalid input
            return; // Exit the method
        }
        
        if (portfolio.containsKey(stockName)) {
            portfolio.put(stockName, portfolio.get(stockName) + quantity);
        } else {
            portfolio.put(stockName, quantity);
        }
        
        System.out.printf("You bought %d shares of %s.\n", quantity, stockName);
    }

    private static void viewPortfolio() {
        System.out.println("\nYour Portfolio:");
        if (portfolio.isEmpty()) {
            System.out.println("Your portfolio is empty.");
        } else {
            for (String stock : portfolio.keySet()) {
                System.out.printf("%s - %d shares\n", stock, portfolio.get(stock));
            }
        }
    }
}