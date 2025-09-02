package nl.hartenjagen;

import java.util.ArrayList;

public class Player {
    public int playerNr;
    public int score;
    public Player nextPlayer;
    public ArrayList<Card> hand = new ArrayList<>();

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

    public ArrayList<Card> getHand() {
        return this.hand;
    }


    //play card
    //can only sleect hand
    //if no points have been played
    //follow suit if you can


}
