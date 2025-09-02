package nl.hartenjagen;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestCreation {
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
}
