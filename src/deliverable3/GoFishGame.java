/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package deliverable3;

import java.util.Scanner;

public class GoFishGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        // Deal 5 cards to each player
        for (int i = 0; i < 5; i++) {
            player1.addCard(deck.drawCard());
            player2.addCard(deck.drawCard());
        }

        while (true) {
            System.out.println(player1.getName() + "'s turn:");
            playTurn(player1, player2, deck, scanner);
            if (checkGameOver(player1, player2, deck)) {
                break;
            }

            System.out.println(player2.getName() + "'s turn:");
            playTurn(player2, player1, deck, scanner);
            if (checkGameOver(player1, player2, deck)) {
                break;
            }
        }

        scanner.close();
    }

    private static void playTurn(Player currentPlayer, Player opponent, DeckOfCards deck, Scanner scanner) {
        System.out.println(currentPlayer.getName() + "'s hand: " + currentPlayer.getHand());

        Card requestedCard = currentPlayer.requestCard();
        System.out.println(currentPlayer.getName() + " asks for a " + requestedCard.getRank() + ".");

        if (opponent.hasCard(requestedCard)) {
            System.out.println(opponent.getName() + " has the card.");
            opponent.removeCard(requestedCard);
            currentPlayer.addCard(requestedCard);
        } else {
            System.out.println(opponent.getName() + " says 'Go Fish!'");
            Card drawnCard = deck.drawCard();
            currentPlayer.addCard(drawnCard);
            System.out.println(currentPlayer.getName() + " draws a " + drawnCard + ".");
        }

        currentPlayer.checkForBooks();
    }

    private static boolean checkGameOver(Player player1, Player player2, DeckOfCards deck) {
        if (player1.getBookCount() + player2.getBookCount() == 13) {
            System.out.println("Game over!");
            if (player1.getBookCount() > player2.getBookCount()) {
                System.out.println(player1.getName() + " wins!");
            } else if (player2.getBookCount() > player1.getBookCount()) {
                System.out.println(player2.getName() + " wins!");
            } else {
                System.out.println("It's a tie!");
            }
            return true;
        }
        return false;
    }
}
