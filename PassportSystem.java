import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PassportSystem {

    public static Scanner scanner = new Scanner(System.in);
    private static LocalDate appointmentDate; 
    private static String appointmentCenter; 

    public static void main(String[] args) {

        System.out.println("Welcome to the Passport System!");

        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Register");
            System.out.println("2. Get to know more");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); 
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                scanner.nextLine(); 
                continue; 
            }
            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    getToKnowMore();
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
        System.out.println("\nLet's register you for passport services.");

        String name;
        do {
            System.out.print("Enter your name (max 50 characters): ");
            name = scanner.nextLine();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty. Please enter your name.");
            } else if (name.length() > 50) {
                System.out.println("Name exceeds 50 characters limit. Please enter a shorter name.");
            } else if (containsDigit(name)) {
                System.out.println("Name cannot contain digits. Please enter a valid name.");
            }
        } while (name.isEmpty() || name.length() > 50 || containsDigit(name));

        // Initialize age
        int age = 0;

        // Read age
        do {
            System.out.print("Enter your age (must be under 120): ");
            try {
                age = scanner.nextInt();
                scanner.nextLine(); // consume newline character after nextInt()
            } catch (InputMismatchException e) {
                System.out.println("Invalid input for age. Please enter a valid number.");
                scanner.nextLine(); // clear the input buffer
                continue;
            }
            if (age <= 0 || age >= 120) {
                System.out.println("Invalid age. Age must be between 1 and 119.");
            }
        } while (age <= 0 || age >= 120);

        // Read address
        String address;
        do {
            System.out.print("Enter your address (below 80 words): ");
            address = scanner.nextLine();
            if (address.isEmpty()) {
                System.out.println("Address cannot be empty. Please enter your address.");
            } else if (address.split("\\s+").length > 80) {
                System.out.println("Address exceeds 80 words limit. Please enter a shorter address.");
            }
        } while (address.isEmpty() || address.split("\\s+").length > 80);

        // Read contact information
        String contact;
        do {
            System.out.print("Enter your contact number (10 digits): ");
            contact = scanner.nextLine();
            if (contact.isEmpty()) {
                System.out.println("Contact number cannot be empty. Please enter your contact number.");
            } else if (!contact.matches("\\d{10}")) {
                System.out.println("Invalid contact number format. Please enter exactly 10 digits.");
            }
        } while (contact.isEmpty() || !contact.matches("\\d{10}"));

        
        System.out.println("\nYou have successfully registered with the Passport System!");

        
        System.out.println("\nWhat would you like to do now?");
        System.out.println("1. Book Appointment for Application Submission");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");

        int choice;
        try {
            choice = scanner.nextInt();
            scanner.nextLine(); 
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Returning to main menu.");
            return;
        }

        switch (choice) {
            case 1:
                bookApplicationSubmission();
                break;
            case 2:
                System.out.println("Thank you for using the Passport System. Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    public static void bookApplicationSubmission() {
        System.out.println("\nYou have chosen Application Submission.");

      
        LocalDate today = LocalDate.now();
        LocalDate maxDate = today.plusDays(60);
        if (appointmentDate == null) {
            System.out.print("Enter your preferred date for the appointment (DD-MM-YY or D-M-YY): ");
            String dateString = scanner.nextLine();

            LocalDate preferredDate;
            try {
                preferredDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd-MM-yy"));
            } catch (Exception e) {
                try {
                    preferredDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("d-M-yy"));
                } catch (Exception ex) {
                    System.out.println("Invalid date format. Please enter date in DD-MM-YY or D-M-YY format.");
                    return;
                }
            }

            if (preferredDate.isBefore(today) || preferredDate.isAfter(maxDate)) {
                System.out.println("Invalid date. You can only book appointments from today up to " + maxDate.format(DateTimeFormatter.ofPattern("dd-MM-yy")) + ".");
                return;
            }

            appointmentDate = preferredDate;
        }
        if (appointmentCenter == null) {
            System.out.println("\nSelect your preferred center for Application Submission:");
            System.out.println("1. Coimbatore");
            System.out.println("2. Salem");
            System.out.println("3. Chennai");
            System.out.print("Enter your choice: ");

            int centerChoice;
            try {
                centerChoice = scanner.nextInt();
                scanner.nextLine(); 
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); 
                return;
            }

            switch (centerChoice) {
                case 1:
                    appointmentCenter = "Coimbatore";
                    break;
                case 2:
                    appointmentCenter = "Salem";
                    break;
                case 3:
                    appointmentCenter = "Chennai";
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                    return;
            }
        }
        System.out.println("Your appointment for Application Submission on " + appointmentDate.format(DateTimeFormatter.ofPattern("dd-MM-yy")) + " at " + appointmentCenter + " is booked.");

        bookDocumentVerification();
    }

    public static void bookDocumentVerification() {
        System.out.println("\nYou have chosen Document Verification.");

        if (appointmentDate == null) {
            System.out.print("Enter your preferred date for the appointment (DD-MM-YY or D-M-YY): ");
            String dateString = scanner.nextLine();

            LocalDate preferredDate;
            try {
                preferredDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd-MM-yy"));
            } catch (Exception e) {
                try {
                    preferredDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("d-M-yy"));
                } catch (Exception ex) {
                    System.out.println("Invalid date format. Please enter date in DD-MM-YY or D-M-YY format.");
                    return;
                }
            }

            if (preferredDate.isBefore(LocalDate.now())) {
                System.out.println("Invalid date. Appointment date cannot be in the past.");
                return;
            }
            appointmentDate = preferredDate;
        }
        System.out.println("Your appointment for Document Verification on " + appointmentDate.format(DateTimeFormatter.ofPattern("dd-MM-yy")) + " is confirmed.");

        System.out.print("Do you have Aadhar Card for verification?\n1. Yes\n2. No\nEnter your choice: ");
        int aadharChoice;
        try {
            aadharChoice = scanner.nextInt();
            scanner.nextLine(); 
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Returning to main menu.");
            return;
        }

        if (aadharChoice == 1) {
            System.out.println("Please bring the following documents along when coming for verification:");
            System.out.println("- Aadhar Card");
            System.out.println("- Birth Certificate");
            System.out.println("- HSC Certificate");
            System.out.println("- Bonafide Certificate");
            System.out.println("- ID Card");
            System.out.println("- PAN Card");
            System.out.println("- Driving License");
        } else if (aadharChoice == 2) {
            System.out.println("Please come directly on the appointment date for further procedures.");
            System.out.println("Bring all documents you have along with their originals.");
        } else {
            System.out.println("Invalid choice. Returning to main menu.");
            return;
        }

        System.out.println("\nWould you like to book another appointment?");
        System.out.println("1. Yes");
        System.out.println("2. No, Exit");
        System.out.print("Enter your choice: ");
        int choice;
        try {
            choice = scanner.nextInt();
            scanner.nextLine(); 
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Returning to main menu.");
            return;
        }

        switch (choice) {
            case 1:
                appointmentDate = null;
                appointmentCenter = null;
                bookApplicationSubmission();
                break;
            case 2:
                System.out.println("Thank you for using the Passport System. Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    public static void getToKnowMore() {
        System.out.println("\nHere is more information about our Passport System:");
        System.out.println("Our system provides services for passport application submission and document verification.");
        System.out.println("You can register for these services and book appointments according to your convenience.");
        System.out.println("If you have any specific queries, please contact our support team at support@passportsystem.com.");
    }

    private static boolean containsDigit(String s) {
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
}
