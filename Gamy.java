import java.util.Random;
import java.util.Scanner;

class Player {
    String name;
    int id;
    int score;
    int currentLevel;

    public Player(String name, int id) {
        this.name = name;
        this.id = id;
        this.score = 0;
        this.currentLevel = 1;
    }
}

public class Gamy {
    private static final int MAX_LEVEL = 3;
    private static final int[][] LEVELS = {{10, 3}, {20, 4}, {100, 6}};
    private static int highestScore = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        Player player = new Player(name, 1);

        do {
            playLevel(player);
            System.out.print("Do you want to continue playing? (yes/no): ");
        } while (scanner.nextLine().equalsIgnoreCase("yes") && player.currentLevel < MAX_LEVEL);

        System.out.println("Thank you for playing! Your final score: " + player.score);
        System.out.println("Highest score: " + highestScore);
        scanner.close();
    }

    private static void playLevel(Player player) {
        int upperBound = LEVELS[player.currentLevel - 1][0];
        int trials = LEVELS[player.currentLevel - 1][1];
        Random random = new Random();
        int numberToGuess = random.nextInt(upperBound + 1);
        boolean guessedCorrectly = false;

        System.out.println("\n--- Level " + player.currentLevel + " ---");
        System.out.println("Guess a number between 0 and " + upperBound + ". You have " + trials + " trials.");

        for (int i = 0; i < trials; i++) {
            System.out.print("Trial " + (i + 1) + ": ");
            int userGuess = new Scanner(System.in).nextInt();

            if (userGuess == numberToGuess) {
                System.out.println("Congratulations! You guessed the number.");
                player.score += 10; // Increment score for correct guess
                guessedCorrectly = true;
                break;
            } else if (userGuess < numberToGuess) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Too high! Try again.");
            }
        }

        if (!guessedCorrectly) {
            System.out.println("Game over! The correct number was " + numberToGuess + ".");
        }

        // Update highest score
        if (player.score > highestScore) {
            highestScore = player.score;
        }

        player.currentLevel++;
    }
}