import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SourceCodeTokenizer {
    public static void main(String[] args) {
        // Step 1: Read the source code from the file
        String sourceCode = readSourceCode("input.java");

        // Step 2: Define the delimiters
        String delimiters = "\\s+|,\\s*|\\(|\\)|\\{|\\}|\\[|\\];"; // Example delimiters for Java

        // Step 3: Tokenize the source code
        String[] tokens = sourceCode.split(delimiters);

        // Step 4: Process the tokens
        for (String token : tokens) {
            System.out.println(token);
            // Additional processing logic can be added here
        }
    }

    private static String readSourceCode(String fileName) {
        StringBuilder sourceCode = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                sourceCode.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sourceCode.toString();
    }
}