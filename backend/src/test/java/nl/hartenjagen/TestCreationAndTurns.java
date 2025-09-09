package nl.hartenjagen;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestCreationAndTurns {
    @Test
    public void Test32CardsAreMade() {
        Game game = new Game();
        assertEquals(32, game.deck.size());
    }

    @Test
    public void TestDeckHas15TotalPoints() {
        int totalPoints = 0;
        Game game = new Game();
        for (Card c: game.deck) {
            totalPoints += c.getPoints();
        }
        assertEquals(15, totalPoints);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    public void TestEachPlayerReceives8Cards(int value) {
        Game game = new Game();
        game.dealCards();
        Player testPlayer = game.players.get(value);
        assertEquals(8, testPlayer.getHand().size());
    }

    @Test
    public void TestDeckIsEmptyAfterDealing() {
        Game game = new Game();
        game.dealCards();
        assertEquals(0, game.deck.size());
    }

    @Test
    public void TestPlayer1HasFirstTurn() {
        Game game = new Game();
        game.dealCards();
        Player p1 = game.players.get(0);
        assertTrue(p1.hasTurn);
    }

    // @Test
    // public void TestPlayer1LosesTurnAfterPlaying() {
    //     Player p2 = new Player(2);
    //     Player p1 = new Player(1, p2);
    //     Card card = new Card(Card.Suit.DIAMONDS, 7);
    //     p1.hand.add(card);
    //     p1.playCard(card);
    //     assertFalse(p1.hasTurn);
    // }

    // @Test
    // public void TestPlayer2GetsTurnAfterPlayer1() {
    //     Player p2 = new Player(2);
    //     Player p1 = new Player(1, p2);
    //     Card card = new Card(Card.Suit.DIAMONDS, 7);
    //     p1.hand.add(card);
    //     p1.playCard(card);
    //     assertTrue(p2.hasTurn);
    // }

    @Test
    public void TestNoOneHasTurnWhenPitEnd() {

    }

    @Test
    public void TestPlayer2StartWithTurnInNextPit() {
        
    }


}
