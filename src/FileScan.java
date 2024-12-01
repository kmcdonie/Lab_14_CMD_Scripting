import javax.swing.*;
import java.io.*;

public class FileScan {
    public static void main(String[] args) {
        File selectedFile = null;

        if (args.length > 0) {
            selectedFile = new File(args[0]);

            if (!selectedFile.exists() || !selectedFile.isFile()) {
                System.out.println("Error: The file '" + args[0] + "' does not exist or is not a valid file.");
                return;
            }
        } else {
            JFileChooser fileChooser = new JFileChooser("src");
            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
            } else {
                System.out.println("File selection canceled.");
                return;
            }
        }

        if (selectedFile != null) {
            processFile(selectedFile);
        }
    }

    private static void processFile(File file) {
        String fileName = file.getName();

        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                lineCount++;
                wordCount += line.split("\\s+").length;
                charCount += line.length();
            }

            // Print the summary report
            System.out.println("\nSummary Report:");
            System.out.println("File Name: " + fileName);
            System.out.println("Number of Lines: " + lineCount);
            System.out.println("Number of Words: " + wordCount);
            System.out.println("Number of Characters: " + charCount);

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}
