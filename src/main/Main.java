package main;

import java.util.Scanner;

import model.User;
import service.UserService;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();

        boolean isRunning = true;

        while (isRunning) {

            System.out.println("\n===== User Management System =====");
            System.out.println("1. Create User");
            System.out.println("2. View All Users");
            System.out.println("3. Find User By ID");
            System.out.println("4. Update User");
            System.out.println("5. Delete User");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    User newUser = new User();

                    System.out.print("Enter user ID: ");
                    newUser.setId(scanner.nextInt());
                    scanner.nextLine();

                    System.out.print("Enter name: ");
                    newUser.setName(scanner.nextLine());

                    System.out.print("Enter email: ");
                    newUser.setEmail(scanner.nextLine());

                    System.out.print("Enter password: ");
                    newUser.setPassword(scanner.nextLine());

                    System.out.print("Enter role (ADMIN/USER): ");
                    newUser.setRole(scanner.nextLine());

                    userService.createUser(newUser);
                    break;

                case 2:
                    System.out.println("\n===== All Users =====");

                    java.util.ArrayList<User> users = userService.getAllUsers();

                    if (users.isEmpty()) {
                        System.out.println("No users found.");
                    } else {
                        for (User user : users) {
                            System.out.println(user);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter user ID to find: ");
                int findId = scanner.nextInt();
                scanner.nextLine();

                User foundUser = userService.findUserById(findId);

                if (foundUser != null) {
                    System.out.println("User found:");
                    System.out.println(foundUser);
                } else {
                    System.out.println("User not found.");
                }
                break;

                case 4:
                    User updateUser = new User();

                    System.out.print("Enter user ID to update: ");
                    updateUser.setId(scanner.nextInt());
                    scanner.nextLine();

                    System.out.print("Enter new name: ");
                    updateUser.setName(scanner.nextLine());

                    System.out.print("Enter new email: ");
                    updateUser.setEmail(scanner.nextLine());

                    System.out.print("Enter new password: ");
                    updateUser.setPassword(scanner.nextLine());

                    System.out.print("Enter new role (ADMIN/USER): ");
                    updateUser.setRole(scanner.nextLine());

                    userService.updateUser(updateUser);
                    break;

                case 5:
                    System.out.print("Enter user ID to delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();

                    boolean isDeleted = userService.deleteUserById(deleteId);

                    if (isDeleted) {
                        System.out.println("User deleted successfully.");
                    } else {
                        System.out.println("User not found.");
                    }
                    break;

                case 6:
                    isRunning = false;
                    System.out.println("Exiting application...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}