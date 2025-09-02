package nl.hartenjagen;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Card> deck = new ArrayList<>();
    ArrayList<Card> table = new ArrayList<>();

    public static void main(String[] args) {
        Game game = new Game();
        game.begin();
    }

    public Game() {
        createPlayers();
        createCards();
    }

    public void createPlayers() {
        Player p4 = new Player(4);
        Player p3 = new Player(3, p4);
        Player p2 = new Player(2, p3);
        Player p1 = new Player(1, p2);
        p4.setNextPlayer(p1);
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
    }

    public void createCards() {
        for (Card.Suit s : Card.Suit.values()) {
            createSuit(s);
        }
    }

    public void createSuit(Card.Suit suit) {
        for (int i = 7; i < 15; i++) {
            deck.add(new Card(suit, i));
        }
    }

    public void begin() {
        dealCards();
        //giveTurnToPlayer
        //get response >  play card
    }

    public void dealCards() {
        Collections.shuffle(deck);
        int playerIndex = 1;
        while (deck.size() != 0) {
            Card card = deck.remove(deck.size() - 1);
            players.get(playerIndex).receiveCard(card);
            playerIndex = (playerIndex + 1) % 4;
        }   
    }
}
