import java.util.Scanner;

public class PassportSystem {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome to the Passport System!");

        while (true) {
            // Menu options
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Register");
            System.out.println("2. Book Appointment");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            // Get user choice
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline character

            // Handle user selection
            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    bookAppointment();
                    break;
                case 3:
                    System.out.println("Thank you for using the Passport System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }
    }

    public static void registerUser() {
        // Registration details
        System.out.println("\nLet's register you for passport services.");
        System.out.print("Enter your name: ");

        // Read name using nextLine()
        String name = scanner.nextLine();

        System.out.print("Enter your age: ");

        // Read age using nextInt()
        int age = scanner.nextInt();
        scanner.nextLine(); // consume newline character

        // Validate age (optional, can add checks for minimum age)
        if (age <= 0) {
            System.out.println("Invalid age. Please enter a positive number.");
            return;
        }

        System.out.print("Enter your address: ");
        String address = scanner.nextLine();
        System.out.print("Enter your contact information: ");
        String contact = scanner.nextLine();

        // Confirmation message
        System.out.println("\nYou have successfully registered with the Passport System!");
    }

    public static void bookAppointment() {
        // Appointment details
        System.out.println("\nLet's book your appointment.");
        System.out.print("What type of service do you need (Application Submission/Document Verification): ");
        String serviceType = scanner.nextLine();
        System.out.print("Enter your preferred date for the appointment (YYYY-MM-DD): ");
        String dateString = scanner.nextLine();


        System.out.println("We are currently checking available appointments for " + serviceType + " on " + dateString + ".");
        System.out.println("** Functionality to check available slots and booking logic goes here!**");
        System.out.println("Assuming your appointment is booked successfully for the chosen date and service.");
    }
}
