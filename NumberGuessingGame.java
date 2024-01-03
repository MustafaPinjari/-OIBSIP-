import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int randomNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;

        int maxAttempts = 5; // You can change this based on your preference
        int score = 0;
        int round = 1;

        System.out.println("Welcome to the Number Guessing Game!");

        while (true) {
            System.out.println("\nRound " + round);
            System.out.println("Guess the number between " + lowerBound + " and " + upperBound + ": ");
            
            for (int attempts = 1; attempts <= maxAttempts; attempts++) {
                int userGuess = scanner.nextInt();

                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You guessed the correct number!");
                    score += maxAttempts - (attempts - 1);
                    break;
                } else {
                    if (userGuess < randomNumber) {
                        System.out.println("Too low! Try again.");
                    } else {
                        System.out.println("Too high! Try again.");
                    }
                }

                if (attempts == maxAttempts) {
                    System.out.println("Sorry, you've run out of attempts. The correct number was: " + randomNumber);
                }
            }

            System.out.println("Your current score is: " + score);
            System.out.println("Do you want to play another round? (yes/no): ");
            
            String playAgain = scanner.next();
            
            if (!playAgain.toLowerCase().equals("yes")) {
                System.out.println("Thanks for playing! Your final score is: " + score);
                break;
            } else {
                round++;
                randomNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            }
        }

        scanner.close();
    }
}
