package nl.hartenjagen;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestPlayCardRules {

    @Test
    public void TestPlayerThatDoesntHaveTurnCantPlay() {
        Game game = new Game();
        Player player = new Player(1);
        Card card = new Card(Card.Suit.DIAMONDS, 7);
        Result result = game.checkIfValid(card, player);
        assertFalse(result.success);
    }

    @Test
    public void TestPlayerCantPlayCardThatsNotInHand() {
        Game game = new Game();
        Player player = new Player(1);
        player.getTurn();
        Card card = new Card(Card.Suit.DIAMONDS, 3);
        Result result = game.checkIfValid(card, player);
        assertFalse(result.success);
    }

    @Test
    public void TestPlayerCanPlayCardThatIsInHand() {
        Game game = new Game();
        Player player = new Player(1);
        player.getTurn();
        Card card = new Card(Card.Suit.DIAMONDS, 7);
        player.receiveCard(card);
        Result result = game.checkIfValid(card, player);
        assertTrue(result.success);        
    }

    @Test
    public void TestWhenPlayerPlaysCardItLeavesHand() {
        Game game = new Game();
        Player p2 = new Player(game, 2);
        Player player = new Player(game, 1, p2);
        player.getTurn();
        Card card = new Card(Card.Suit.DIAMONDS, 7);
        player.receiveCard(card);
        player.playCard(card);     
        assertFalse(player.hasCardInHand(card));  
    }

    @Test
    public void TestWhenPlayerPlaysCardItGoesOnTable() {
        Game game = new Game();
        Player p2 = new Player(game, 2);
        Player player = new Player(game, 1, p2);
        player.getTurn();
        Card card = new Card(Card.Suit.DIAMONDS, 7);
        player.receiveCard(card);
        player.playCard(card);
        assertTrue(game.cardIsOnTable(card));
    }
}
