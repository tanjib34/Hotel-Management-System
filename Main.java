package Hotel.Managemant.System;

import javax.swing.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== HOTEL MANAGEMENT SYSTEM ===");
            System.out.println("1. Splash");
            System.out.println("2.  Login ");
            System.out.println("3.  Dashboard");
            System.out.println("4.  admin ");
            System.out.println("5.  Reception ");
            System.out.println("q. Quit");
            System.out.print("Enter your choice: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println("Opening Splash Screen...");
                    SwingUtilities.invokeLater(() -> new Splash());
                    break;

                case "2":
                    System.out.println("Opening Login Panel...");
                    SwingUtilities.invokeLater(() -> new Login());
                    break;

                case "3":
                    System.out.println("Opening Dashboard...");
                    SwingUtilities.invokeLater(() -> new Dashboard());
                    break;

                case "4":
                    System.out.println("Opening Admin Panel...");
                    SwingUtilities.invokeLater(() -> new admin());
                    break;

                case "5":
                    System.out.println("Opening Reception Panel...");
                    SwingUtilities.invokeLater(() -> new Reception());
                    break;

                case "q":
                case "Q":
                    System.out.println("Exiting System... Thank you for using Hotel Management System!");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println(" Invalid choice! Please try again.");
            }
        }
    }
}
