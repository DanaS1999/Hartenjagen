package nl.hartenjagen;

public class Card {
    public String name;
    public Suit suit;
    public int value;
    public int points;
    public Player playedBy;

    public enum Suit {
        HEARTS, DIAMONDS, SPADES, CLUBS
    }

    public Card(Suit suit, int value) {
        this.suit = suit;
        this.value = value;
        this.name = determineName(suit, value);
        determinePoints(suit, value);
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return this.points;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public int getValue() {
        return this.value;
    }
    
    public String determineName(Suit suit, int value) {
        String s = suit.toString().toLowerCase();
        String suitName = s.substring(0,1).toUpperCase() + s.substring(1);
        switch (value) {
            case 14:
                return "Ace of" + suitName;
            case 13:
                return "King of" + suitName;
            case 12:
                return "Queen of" + suitName;
            case 11:
                return "Jack of" + suitName;
            default:
                return String.valueOf(value) + "of" + suitName;
        }
    }
    
    public void determinePoints(Suit suit, int value) {
        if (suit == Suit.HEARTS) {
            setPoints(1);
        }
        else if (suit == Suit.SPADES && value == 12) {
            setPoints(5);
        }
        else if (suit == Suit.CLUBS && value == 11) {
            setPoints(2);
        }   
    }

    public void playedBy(Player player) {
        this.playedBy = player;
    }
}

