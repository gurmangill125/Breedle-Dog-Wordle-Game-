import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

class Breed {
    private String breedName;

    public Breed(String breedName) {
        this.breedName = breedName.toLowerCase();
    }

    public String getBreedName() {
        return breedName;
    }

    public String generateBreedHint(String guess) {
        char[] hint = new char[breedName.length()];
        Arrays.fill(hint, '_');

        for (int i = 0; i < guess.length() && i < breedName.length(); i++) {
            if (guess.charAt(i) == breedName.charAt(i)) {
                hint[i] = breedName.charAt(i);
            }
        }
        return new String(hint);
    }
}

class Player {
    private Scanner scanner = new Scanner(System.in);

    public String guessBreed(int breedLength) {
        while (true) {
            String guess = scanner.nextLine().toLowerCase();
            if (guess.length() == breedLength) {
                return guess;
            } else {
                System.out.println("Your guess must have the same length as the breed name. Try again!");
            }
        }
    }
}

public class Game {
    private Player player;
    private Breed breed;
    private static String[] dogBreeds = {"labradorretriever", "bulldog", "beagle", "poodle", "boxer"};
    private static int MAX_ATTEMPTS = 5;

    public Game() {
        this.player = new Player();
        this.breed = new Breed(dogBreeds[new Random().nextInt(dogBreeds.length)]);
    }

    public void start() {
        System.out.println("Welcome to Breedle - Dog Breed Guessing Game!");

        String breedName = breed.getBreedName();
        System.out.println("The breed name has " + breedName.length() + " characters: " + new String(new char[breedName.length()]).replace("\0", "_ "));

        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
            System.out.println("\nAttempt " + attempt + ": ");
            String guess = player.guessBreed(breedName.length());

            if (guess.equals(breedName)) {
                System.out.println("Congratulations! You've guessed the breed correctly.");
                return;
            } else {
                System.out.println(breed.generateBreedHint(guess));
            }
        }

        System.out.println("Sorry, you didn't guess the breed. The correct breed was: " + breedName);
    }

    public static void main(String[] args) {
        new Game().start();
    }
}
