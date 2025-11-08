package Hotel.Managemant.System;

import java.util.Scanner;

public class DashboardConsole {
    private Scanner scanner;
    private AdminConsole admin;
    // We'll create ReceptionConsole later if needed

    public DashboardConsole() {
        scanner = new Scanner(System.in);
        admin = new AdminConsole();
    }

    public void showMainMenu() {
        while (true) {
            displayWelcomeHeader();

            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Admin Login");
            System.out.println("2. Reception");
            System.out.println("3. Exit System");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    if (authenticateAdmin()) {
                        admin.showAdminMenu();
                    }
                    break;
                case 2:
                    showReceptionMenu();
                    break;
                case 3:
                    System.out.println("\nThank you for using Hotel Management System!");
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option! Please choose between 1-3.");
                    pressEnterToContinue();
            }
        }
    }

    private void displayWelcomeHeader() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("           WELCOME TO HOTEL MANAGEMENT SYSTEM");
        System.out.println("=".repeat(60));
        System.out.println();
    }

    private boolean authenticateAdmin() {
        System.out.println("\n--- Admin Login ---");
        System.out.print("Enter Admin Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Admin Password: ");
        String password = scanner.nextLine();

        // Simple authentication - you can make this more secure
        if (username.equals("admin") && password.equals("admin123")) {
            System.out.println("✓ Login successful! Welcome Admin!");
            return true;
        } else {
            System.out.println("✗ Invalid username or password!");
            pressEnterToContinue();
            return false;
        }
    }

    private void showReceptionMenu() {
        System.out.println("\n=== RECEPTION MENU ===");
        System.out.println("1. Check-in Guest");
        System.out.println("2. Check-out Guest");
        System.out.println("3. View Available Rooms");
        System.out.println("4. Back to Main Menu");
        System.out.print("Choose option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Check-in feature coming soon...");
                pressEnterToContinue();
                break;
            case 2:
                System.out.println("Check-out feature coming soon...");
                pressEnterToContinue();
                break;
            case 3:
                viewAvailableRooms();
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private void viewAvailableRooms() {
        System.out.println("\n--- Available Rooms ---");
        // This would integrate with our room system
        FileBasedDB db = new FileBasedDB();
        java.util.List<String[]> rooms = db.getAllRooms();

        boolean foundAvailable = false;
        for (String[] room : rooms) {
            if ("Available".equals(room[1]) && "Cleaned".equals(room[2])) {
                if (!foundAvailable) {
                    System.out.println("Room No | Price | Bed Type");
                    System.out.println("---------------------------");
                    foundAvailable = true;
                }
                System.out.println(room[0] + " | " + room[3] + " | " + room[4]);
            }
        }

        if (!foundAvailable) {
            System.out.println("No available rooms at the moment.");
        }
        pressEnterToContinue();
    }

    private void pressEnterToContinue() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public static void main(String[] args) {
        DashboardConsole dashboard = new DashboardConsole();
        dashboard.showMainMenu();
    }
}