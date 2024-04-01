package TokenCategorizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TokenCategorizer {
    public static void main(String[] args) {
        // Map to store categorized tokens
        Map<String, String> categorizedTokens = new HashMap<>();

        // Read Java source code file
        try (BufferedReader br = new BufferedReader(new FileReader("input.java"))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Tokenize the line using String.split()
                String[] tokens = line.split("\\s+|\\(|\\)|\\{|\\}|\\[|\\]|\\.|\\,|;|\\+|\\-|\\*|\\/|\\%");
                
                // Categorize tokens and store them in the map
                for (String token : tokens) {
                    if (!token.isEmpty()) {
                        String category = categorizeToken(token);
                        categorizedTokens.put(token, category);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Output categorized tokens
        for (Map.Entry<String, String> entry : categorizedTokens.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    // Function to categorize tokens
    public static String categorizeToken(String token) {
        if (token.matches("[a-zA-Z]+")) {
            return "Identifier";
        } else if (token.matches("[0-9]+")) {
            return "Literal";
        } else if (token.equals("if") || token.equals("else") || token.equals("while") || token.equals("for")) {
            return "Keyword";
        } else if (token.matches("[\\{\\}\\(\\)\\[\\];,\\+\\-\\*/\\%\\.]")) {
            return "Operator/Delimiter";
        } else {
            return "Unknown";
        }
    }
}
