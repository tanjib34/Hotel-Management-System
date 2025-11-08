package Hotel.Managemant.System;

import java.util.Scanner;

public class Admin {
    private Scanner scanner;
    private AddEmployee addEmployeeConsole;
    private AddRoom addRoomConsole;
    private addDriver addDriverConsole;

    public Admin() {
        scanner = new Scanner(System.in);
        AddEmployee = new AddEmployee();
        AddRoom  = new AddRoom();
        addDriver = new addDriver();
    }

    public void showAdminMenu() {
        while (true) {
            displayAdminHeader();

            System.out.println("\n=== ADMIN DASHBOARD ===");
            System.out.println("1. Employee Management");
            System.out.println("2. Room Management");
            System.out.println("3. Driver Management");
            System.out.println("4. Back to Main Menu");
            System.out.println("5. Logout");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    employeeManagement();
                    break;
                case 2:
                    roomManagement();
                    break;
                case 3:
                    driverManagement();
                    break;
                case 4:
                    System.out.println("Returning to main menu...");
                    return;
                case 5:
                    System.out.println("Logging out... Thank you for using Hotel Management System!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option! Please choose between 1-5.");
                    pressEnterToContinue();
            }
        }
    }

    private void employeeManagement() {
        while (true) {
            System.out.println("\n=== EMPLOYEE MANAGEMENT ===");
            System.out.println("1. Add New Employee");
            System.out.println("2. Back to Admin Menu");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addEmployeeConsole.AddEmployeeMenu();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void roomManagement() {
        while (true) {
            System.out.println("\n=== ROOM MANAGEMENT ===");
            System.out.println("1. Add New Room");
            System.out.println("2. Back to Admin Menu");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addRoomConsole.addRoomMenu();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void driverManagement() {
        while (true) {
            System.out.println("\n=== DRIVER MANAGEMENT ===");
            System.out.println("1. Add New Driver");
            System.out.println("2. Back to Admin Menu");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addDriverConsole.addDriverMenu();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void displayAdminHeader() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("          HOTEL MANAGEMENT SYSTEM");
        System.out.println("               ADMIN PANEL");
        System.out.println("=".repeat(50));
    }

    private void pressEnterToContinue() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }

    // Simple version without the complex sub-menus that were causing errors
    public void showQuickAdminMenu() {
        while (true) {
            System.out.println("\n--- Admin Options ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Add Room");
            System.out.println("3. Add Driver");
            System.out.println("4. Back");
            System.out.print("Select: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addEmployeeConsole.AddEmployeeMenu();
                    break;
                case 2:
                    addRoomConsole.addRoomMenu();
                    break;
                case 3:
                    addDriverConsole.addDriverMenu();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static void main(String[] args) {
        AdminConsole adminConsole = new AdminConsole();
        adminConsole.showAdminMenu();
    }
}