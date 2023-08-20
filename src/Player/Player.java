/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Player;


public class Player {
    private final String name;
    private final List<Card> hand;
    private final Map<String, Integer> books;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.books = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public Card requestCard() {
        Random rand = new Random();
        return hand.get(rand.nextInt(hand.size()));
    }

    public boolean hasCard(Card card) {
        return hand.contains(card);
    }

    public void removeCard(Card card) {
        hand.remove(card);
    }

    public void checkForBooks() {
        Map<String, Integer> rankCounts = new HashMap<>();

        for (Card card : hand) {
            rankCounts.put(card.getRank(), rankCounts.getOrDefault(card.getRank(), 0) + 1);
        }

        for (String rank : rankCounts.keySet()) {
            if (rankCounts.get(rank) == 4) {
                books.put(rank, books.getOrDefault(rank, 0) + 1);
                removeCardsWithRank(rank);
            }
        }
    }

    private void removeCardsWithRank(String rank) {
        Iterator<Card> iterator = hand.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getRank().equals(rank)) {
                iterator.remove();
            }
        }
    }

    public int getBookCount() {
        return books.size();
    }
}
