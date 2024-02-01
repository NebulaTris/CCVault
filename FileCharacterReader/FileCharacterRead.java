import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileCharacterRead {

    public static void main(String[] args) {
        // Specify the file path
        String filePath = "test.txt";

        // Create a File object
        File file = new File(filePath);

        try {
            // Check if the file exists
            if (file.exists()) {
                // Open the file for reading
                java.io.FileInputStream fis = new java.io.FileInputStream(file);

                // Read character by character
                int data;
                while ((data = fis.read()) != -1) {
                    char character = (char) data;
                    // Process the character (you can print or manipulate it as needed)
                    System.out.print(character);
                }

                // Close the FileInputStream
                fis.close();
            } else {
                System.out.println("File not found.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}



