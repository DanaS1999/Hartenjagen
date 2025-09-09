package nl.hartenjagen;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Card> deck = new ArrayList<>();
    ArrayList<Card> table = new ArrayList<>();
    int playerIndex = 0;
    boolean pointsWerePlayed = false;
    boolean countingUp = true;

    public static void main(String[] args) {
        Game game = new Game();
        game.startRound();
    }

    public Game() {
        createPlayers();
        createCards();
    }

    public void createPlayers() {
        Player p4 = new Player(this, 4);
        Player p3 = new Player(this, 3, p4);
        Player p2 = new Player(this, 2, p3);
        Player p1 = new Player(this, 1, p2);
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

    public void startRound() {
        dealCards();
    }

    public void dealCards() {
        Collections.shuffle(deck);
        while (deck.size() != 0) {
            Card card = deck.remove(deck.size() - 1);
            players.get(playerIndex).receiveCard(card);
            playerIndex = (playerIndex + 1) % 4;
        }   
        players.get(playerIndex).getTurn();
        playerIndex += 1;
    }

    public Result checkIfValid(Card card, Player p) {
        if (!p.hasTurn()) {
            return new Result(false, "Player does not have turn");
        } else if (!p.hasCardInHand(card)) {
            return new Result(false, "Player does not have this card");
        } else if (!playerFollowsSuit(card, p)) {
            return new Result(false, "Player must play a card of the same suit as the first card");
        }
         else if (card.getPoints() > 0 && !pointsWerePlayed) {
            return new Result(false, "Player cannot play a card with points yet");
        } else {
            return new Result(true);
        }
    }

    public boolean playerFollowsSuit(Card card, Player player) {
        if (table.size() == 0) {
            return true;
        } else {
            Card firstCard = table.get(0);
            Card.Suit firstSuit = card.getSuit();
            if (firstCard.getSuit() == card.getSuit()) {
                return true;
            } else {
                return (player.cannotFollowSuit(firstSuit));
            }
        }
    }

    public boolean cardFollowsSuit(Card card) {
       if (table.size() == 0) {
            return true;
        } else {
            Card firstCard = table.get(0);
            if (firstCard.getSuit() == card.getSuit()) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean roundCompleted() {
        return table.size() == 4;
    }
    
    public void endOfRound() {
        int totalPointsInRound = 0;
        Card highestCardInRound = table.get(0);
        for (Card c : table) {
            totalPointsInRound += c.getPoints();
            if (cardFollowsSuit(c) && (c.getValue() > highestCardInRound.getValue())) {
                highestCardInRound = c;
            }
        }
        Player roundWinner = highestCardInRound.playedBy;
        roundWinner.increaseScore(totalPointsInRound);
        returnTableToDeck();
        if (deck.size() != 32) {
            roundWinner.getTurn();
        } else {
            endOfHand();
        }
    }

    public boolean cardIsOnTable(Card card) {
        return table.contains(card);
    }

    public void putCardOnTable(Card card, Player player) {
        card.playedBy(player);
        table.add(card);
    }

    public void endOfHand() {
        boolean max35pointsReached = false;
        for (Player p : players) {
            if (p.getScore() >= 35) {
                p.capScoreAt35();
                max35pointsReached = true;
            }
        }
        if (countingUp && max35pointsReached) {
            countingUp = false;
        } 
        dealCards();
    }
    
    public void returnTableToDeck() {
        for (Card c : table) {
            table.remove(c);
            deck.add(c);
        }
    }

}
