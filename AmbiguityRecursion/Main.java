import java.util.*;

class Grammar {
    private Set<Character> nonTerminals;
    private Set<Character> terminals;
    private Map<Character, List<String>> productions;

    public Grammar() {
        nonTerminals = new HashSet<>();
        terminals = new HashSet<>();
        productions = new HashMap<>();
    }

    public void addProduction(char nonTerminal, String production) {
        nonTerminals.add(nonTerminal);
        String[] tokens = production.split("\\|");
        List<String> productionList = productions.getOrDefault(nonTerminal, new ArrayList<>());
        for (String token : tokens) {
            productionList.add(token);
            for (char c : token.toCharArray()) {
                if (!Character.isUpperCase(c)) {
                    terminals.add(c);
                }
            }
        }
        productions.put(nonTerminal, productionList);
    }

    public void printGrammar() {
        System.out.println("Grammar:");
        for (char nt : nonTerminals) {
            System.out.print(nt + " -> ");
            List<String> productionList = productions.get(nt);
            for (String production : productionList) {
                System.out.print(production + " | ");
            }
            System.out.println();
        }
    }

    public boolean isAmbiguous() {
        for (char nt : nonTerminals) {
            Set<String> productionSet = new HashSet<>();
            List<String> productionList = productions.get(nt);
            for (String production : productionList) {
                if (!productionSet.add(production)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void removeLeftRecursion() {
        for (char A : nonTerminals) {
            List<String> alpha = new ArrayList<>();
            List<String> beta = new ArrayList<>();
            List<String> productionList = productions.get(A);
            for (String production : productionList) {
                if (production.charAt(0) == A) {
                    alpha.add(production.substring(1));
                } else {
                    beta.add(production);
                }
            }
            if (!alpha.isEmpty()) {
                char newNonTerminal = getNewNonTerminal();
                nonTerminals.add(newNonTerminal);
                for (String b : beta) {
                    addProduction(A, b + newNonTerminal);
                }
                addProduction(newNonTerminal, "");
                for (String a : alpha) {
                    addProduction(newNonTerminal, a + newNonTerminal);
                }
                addProduction(newNonTerminal, "");
                productionList.clear();
            }
        }
    }

    private char getNewNonTerminal() {
        char newNonTerminal = 'A';
        while (nonTerminals.contains(newNonTerminal)) {
            newNonTerminal++;
        }
        return newNonTerminal;
    }
}

public class Main {
    public static void main(String[] args) {
        Grammar grammar = new Grammar();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the grammar (enter 'end' to finish):");
        while (true) {
            System.out.print("Non-terminal: ");
            String line = scanner.nextLine();
            if (line.equals("end")) {
                break;
            }
            char nonTerminal = line.charAt(0);
            System.out.print("Production (separate alternatives with '|'): ");
            String production = scanner.nextLine();
            grammar.addProduction(nonTerminal, production);
        }
        scanner.close();
        System.out.println("\nOriginal Grammar:");
        grammar.printGrammar();
        if (grammar.isAmbiguous()) {
            System.out.println("\nAmbiguity detected in the grammar.");
        } else {
            System.out.println("\nNo ambiguity detected in the grammar.");
        }
        grammar.removeLeftRecursion();
        System.out.println("\nGrammar after removing left recursion:");
        grammar.printGrammar();
    }
}
