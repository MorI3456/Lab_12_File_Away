import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileInspector {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        // Set JFileChooser to open in the src directory of the project
        fileChooser.setCurrentDirectory(new File("src"));

        // Show the file chooser dialog
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            Path filePath = Paths.get(selectedFile.getAbsolutePath());

            // Variables to store summary report data
            int lineCount = 0;
            int wordCount = 0;
            int charCount = 0;

            try (BufferedReader reader = Files.newBufferedReader(filePath)) {
                String line;
                System.out.println("File Content:");

                while ((line = reader.readLine()) != null) {
                    System.out.println(line); // Echo each line to the screen
                    lineCount++;
                    wordCount += line.split("\\s+").length; // Count words in the line
                    charCount += line.length(); // Count characters in the line
                }

                // Print the summary report
                System.out.println("\nSummary Report:");
                System.out.println("File Name: " + selectedFile.getName());
                System.out.println("Number of Lines: " + lineCount);
                System.out.println("Number of Words: " + wordCount);
                System.out.println("Number of Characters: " + charCount);

            } catch (IOException e) {
                System.err.println("Error reading the file: " + e.getMessage());
            }
        } else {
            System.out.println("No file selected.");
        }
    }
}
