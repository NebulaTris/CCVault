public class Input {
    public static void main(String[] args) {
        String inputString = "This is a sample input string for tokenization.";
        int number = 100;
        for (int i = 0; i < number; i++) {
            System.out.println("Token " + i + ": " + inputString.substring(i, i + 1));
        }
    }
}