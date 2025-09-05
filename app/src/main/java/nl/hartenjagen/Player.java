package nl.hartenjagen;

import java.util.ArrayList;

public class Player {
    public Game game;
    public int playerNr;
    public int score;
    public Player nextPlayer;
    public boolean hasTurn = false;
    public ArrayList<Card> hand = new ArrayList<>();

    public Player(Game game, int playerNr) {
        this.game = game;
        this.playerNr = playerNr;
    }

    public Player(Game game, int playerNr, Player nextPlayer) {
        this.game = game;
        this.playerNr = playerNr;
        this.nextPlayer = nextPlayer;
    }

    public Player(int playerNr) {
        this.playerNr = playerNr;
    }

    public Player(int playerNr, Player nextPlayer) {
        this.playerNr = playerNr;
        this.nextPlayer = nextPlayer;
    }

    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public int getScore() {
        return this.score;
    }

    public void capScoreAt35() {
        this.score = 35;
    }

    public void increaseScore(int pointsFromRound) {
        this.score += pointsFromRound;
    }

    public ArrayList<Card> getHand() {
        return this.hand;
    }

    public boolean hasCardInHand(Card card) {
        return getHand().contains(card);
    }

    public boolean hasTurn() {
        return this.hasTurn;
    }

    public void getTurn() {
        this.hasTurn = true;
    }

    public void loseTurn() {
        this.hasTurn = false;
    }

    public boolean cannotFollowSuit(Card.Suit suit) {
        for (Card c : hand) {
            if (c.getSuit() == suit) {
                return false;
            }
        }
        return true;
    }

    public void giveTurnToNeighbour() {
        getNextPlayer().getTurn();
    }

    public void removeCardFromHand(Card card) {
        getHand().remove(card);
    }

    public void playCard(Card card) {
        Result result = game.checkIfValid(card, this);
        if (result.success) {
            removeCardFromHand(card);
            game.putCardOnTable(card, this);
            loseTurn();
            if (!game.roundCompleted()) {
                giveTurnToNeighbour();   
            } else {
                game.endOfRound();
            }
        }
    }




}
