package pokerBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pokerEnums.eRank;
import pokerEnums.eSuit;
import pokerExceptions.DeckException;

public class Deck_Test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void BuildDeckTest() {
		Deck d = new Deck(0);
		assertTrue(d.GetDeckSize() == 52);
	}

	@Test
	public void DeckDrawTest() throws DeckException {
		Deck d = new Deck(0);
		d.Draw();
		assertTrue(d.GetDeckSize() == 51);
	}

	@Test(expected = DeckException.class)
	public void DeckBuildTestOverDraw() throws DeckException {
		Deck NewDeck = new Deck(0);
		for (int count = 0; count < 53; count++) {
			NewDeck.Draw();
		}
	}

	@Test
	public void TestDeckBuildtWithJoker() {
		Deck NewDeck = new Deck(2);
		int count = 0;
		for (int index = 0; index < ((ArrayList<Card>) NewDeck.getDeckCards()).size(); index++) {
			if (((ArrayList<Card>) NewDeck.getDeckCards()).get(index).geteRank() == eRank.Joker) {
				count += 1;
			}
		}
		assertTrue(count == 2);
	}

	@Test
	public void TestDeckBuildWithWilds() {
		int count = 0;
		ArrayList<Card> Wilds = new ArrayList<Card>();
		
		Wilds.add(new Card(eSuit.CLUBS, eRank.ACE, 39));
		Wilds.add(new Card(eSuit.SPADES, eRank.ACE, 26));
		Wilds.add(new Card(eSuit.HEARTS, eRank.ACE, 13));
		Wilds.add(new Card(eSuit.DIAMONDS, eRank.ACE, 52));
		
		Deck NewDeck = new Deck(0, Wilds);

		for (int index = 0; index < NewDeck.GetDeckSize(); index++) {
			if (NewDeck.getDeckCards().get(index).isbWild()) {
				count += 1;
			}
		}
		assertTrue(count == 4);
	}
}