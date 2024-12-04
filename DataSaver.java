import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> records = new ArrayList<>();
        int idCounter = 1;

        System.out.println("Enter user data. Type 'exit' at any prompt to stop.");

        while (true) {
            System.out.print("First Name: ");
            String firstName = scanner.nextLine();
            if (firstName.equalsIgnoreCase("exit")) break;

            System.out.print("Last Name: ");
            String lastName = scanner.nextLine();
            if (lastName.equalsIgnoreCase("exit")) break;

            String id = String.format("%06d", idCounter++); // Generate a 6-digit ID

            System.out.print("Email: ");
            String email = scanner.nextLine();
            if (email.equalsIgnoreCase("exit")) break;

            System.out.print("Year of Birth: ");
            String yearOfBirth = scanner.nextLine();
            if (yearOfBirth.equalsIgnoreCase("exit")) break;

            // Create CSV record
            String record = String.join(", ", firstName, lastName, id, email, yearOfBirth);
            records.add(record);
        }

        System.out.print("Enter the name of the CSV file (e.g., data.csv): ");
        String fileName = scanner.nextLine();

        // Ensure the src directory exists
        File directory = new File(System.getProperty("user.dir") + "/src/");
        if (!directory.exists()) {
            directory.mkdirs(); // Create src directory if it doesn't exist
        }

        File file = new File(directory, fileName);

        // Write records to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String record : records) {
                writer.write(record);
                writer.newLine();
            }
            System.out.println("Data saved successfully to " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
