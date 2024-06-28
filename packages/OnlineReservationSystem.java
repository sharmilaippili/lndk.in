package packages;
import java.util.HashMap;
import java.util.Scanner;

public class OnlineReservationSystem {
    static HashMap<String, String> users = new HashMap<>(); // For storing user credentials
    static HashMap<String, String> reservations = new HashMap<>(); // For storing reservations with PNR as key

    public static void main(String[] args) {
        users.put("sharmila", "5678");
        users.put("jishwitha", "1234");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Online Reservation System");
        System.out.print("Enter login ID: ");
        String loginId = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        if (authenticateUser(loginId, password)) {
            System.out.println("Login successful!");

            while (true) {
                System.out.println("\n1. Make a Reservation");
                System.out.println("\n2. Cancel a Reservation");
                System.out.println("\n3. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        makeReservation(scanner);
                        break;
                    case 2:
                        cancelReservation(scanner);
                        break;
                    case 3:
                        System.out.println("Exiting system.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }
        } else {
            System.out.println("Invalid login ID or password.");
        }
    }
    private static boolean authenticateUser(String loginId, String password) {
        return users.containsKey(loginId) && users.get(loginId).equals(password);
    }
    private static void makeReservation(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter train number: ");
        String trainNumber = scanner.nextLine();
        System.out.print("Enter class type: ");
        String classType = scanner.nextLine();
        System.out.print("Enter date of journey (YYYY-MM-DD): ");
        String dateOfJourney = scanner.nextLine();
        System.out.print("Enter from (place): ");
        String fromPlace = scanner.nextLine();
        System.out.print("Enter destination: ");
        String toPlace = scanner.nextLine();
        String pnr = "PNR" + (reservations.size() + 1); 
        String reservationDetails = String.format("Name: %s, Train Number: %s, Class: %s, Date: %s, From: %s, To: %s",
                name, trainNumber, classType, dateOfJourney, fromPlace, toPlace);
        
        reservations.put(pnr, reservationDetails);
        System.out.println("Reservation successful! Your PNR: " + pnr);
    }
    private static void cancelReservation(Scanner scanner) {
        System.out.print("Enter PNR number to cancel: ");
        String pnr = scanner.nextLine();
        
        if (reservations.containsKey(pnr)) {
            System.out.println("Reservation Details: " + reservations.get(pnr));
            System.out.print("Confirm cancellation (yes/no): ");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("yes")) {
                reservations.remove(pnr);
                System.out.println("Reservation cancelled successfully.");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("Invalid PNR number.");
        }
    }
}