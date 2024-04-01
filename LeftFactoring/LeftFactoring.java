package LeftFactoring;
import java.util.*;

public class LeftFactoring {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<String>> grammar = new HashMap<>();
        
        // Read and parse user input grammar
        System.out.println("Enter the grammar (non-terminal -> production):");
        String input = scanner.nextLine();
        String[] productions = input.split("->");
        String nonTerminal = productions[0].trim();
        String[] productionRules = productions[1].trim().split("\\|");
        
        // Identify common prefixes
        String commonPrefix = findCommonPrefix(productionRules);
        
        // Extract common prefixes
        String newNonTerminal = nonTerminal + "'";
        List<String> newProductions = new ArrayList<>();
        for (String rule : productionRules) {
            if (rule.startsWith(commonPrefix)) {
                newProductions.add(rule.substring(commonPrefix.length()).trim());
            }
        }
        
        // Update productions
        grammar.put(nonTerminal, Collections.singletonList(commonPrefix + newNonTerminal));
        grammar.put(newNonTerminal, newProductions);
        
        // Display modified grammar
        System.out.println("Modified Grammar:");
        for (Map.Entry<String, List<String>> entry : grammar.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            for (String rule : entry.getValue()) {
                System.out.print(rule + " | ");
            }
            System.out.println();
        }
    }
    
    private static String findCommonPrefix(String[] productionRules) {
        String prefix = "";
        if (productionRules.length > 1) {
            String firstRule = productionRules[0];
            String secondRule = productionRules[1];
            int i = 0;
            while (i < firstRule.length() && i < secondRule.length() && firstRule.charAt(i) == secondRule.charAt(i)) {
                prefix += firstRule.charAt(i);
                i++;
            }
        }
        return prefix;
    }
}