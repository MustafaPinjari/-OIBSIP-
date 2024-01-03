import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        // Step 1: Generate a random number
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1; // Generates a random number between 1 and 100

        // Step 2: Initialize variables
        Scanner scanner = new Scanner(System.in);
        int userGuess;
        int attempts = 0;
        final int MAX_ATTEMPTS = 5; // Change this value if you want a different limit

        System.out.println("Welcome to the Number Guessing Game!");

        // Step 5: Use a loop for multiple attempts
        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter your guess (between 1 and 100): ");

            // Step 3: Prompt the user for input
            userGuess = scanner.nextInt();
            attempts++;

            // Step 4: Compare the user's guess
            if (userGuess == randomNumber) {
                System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                break;
            } else if (userGuess < randomNumber) {
                System.out.println("Too low. Try again.");
            } else {
                System.out.println("Too high. Try again.");
            }

            // Provide feedback on remaining attempts
            int remainingAttempts = MAX_ATTEMPTS - attempts;
            System.out.println("Remaining attempts: " + remainingAttempts);

            // Check if the user has exhausted all attempts
            if (attempts == MAX_ATTEMPTS) {
                System.out.println("Sorry, you've run out of attempts. The correct number was: " + randomNumber);
            }
        }

        // Close the scanner
        scanner.close();
    }
}
