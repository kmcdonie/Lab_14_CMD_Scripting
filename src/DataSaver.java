import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.CREATE;

public class DataSaver {
    public static void main(String[] args) {
        ArrayList<String> csvRecords = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        int idCounter = 1;
        String moreRecords;

        do {
            System.out.println("Enter user data:");

            // Collect data from the user
            String firstName = SafeInput.getNonZeroLenString(in, "First Name");
            String lastName = SafeInput.getNonZeroLenString(in, "Last Name");
            String idNumber = String.format("%06d", idCounter++); // ID as a zero-padded 6-digit number
            String email = SafeInput.getNonZeroLenString(in, "Email");
            int yearOfBirth = SafeInput.getInt(in, "Year of Birth (4 digits)");

            // Create CSV record
            String record = String.format("%s, %s, %s, %s, %d", firstName, lastName, idNumber, email, yearOfBirth);
            csvRecords.add(record);

            // Ask if the user wants to add more records
            moreRecords = SafeInput.getNonZeroLenString(in, "Do you want to add another record? (Y/N)").toLowerCase();

        } while (moreRecords.startsWith("y"));

        // Get the filename from the user
        String fileName = SafeInput.getNonZeroLenString(in, "Enter the CSV file name (e.g., data.csv)");
        if (!fileName.endsWith(".csv")) {
            fileName += ".csv";
        }

        // Write data to the file
        try (FileWriter writer = new FileWriter("src/" + fileName)) {
            for (String record : csvRecords) {
                writer.write(record + "\n");
            }
            System.out.println("Data saved successfully to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file " + e.getMessage());
        }
    }
}
