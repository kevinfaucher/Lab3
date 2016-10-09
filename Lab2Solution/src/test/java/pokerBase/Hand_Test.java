package pokerBase;

import java.util.ArrayList;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pokerEnums.eCardNo;
import pokerEnums.eHandStrength;
import pokerEnums.eRank;
import pokerEnums.eSuit;
import pokerExceptions.HandException;
import pokerExceptions.exHand;
import pokerBase.Hand;

public class Hand_Test {

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

	@Test(expected = HandException.class)
	public void TestEvalShortHand() throws Exception {

		Hand h = new Hand();

		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.ACE, 0));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.ACE, 0));

		h = Hand.EvaluateHand(h);
	}

	@Test
	public void TestJoker1() throws exHand {
		Hand h = new Hand();
		h.AddToCardsInHand(new Card(eSuit.Joker, eRank.Joker, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.JACK, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.QUEEN, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.KING, 1));
		ArrayList<Hand> AllHands = new ArrayList<Hand>();
		AllHands = Hand.getExplodeHands(h);
		Hand BestHand = new Hand();
		BestHand = Hand.PickBestHand(AllHands);
		try {
			BestHand = Hand.EvaluateHand(BestHand);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(BestHand.getHs().getHandStrength() == eHandStrength.RoyalFlush.getHandStrength());
		assertTrue(BestHand.getHs().getHiHand() == eRank.ACE.getiRankNbr());
	}

	@Test
	public void TestJoker2() throws exHand {
		Hand h = new Hand();
		h.AddToCardsInHand(new Card(eSuit.DIAMONDS, eRank.QUEEN, 1));
		h.AddToCardsInHand(new Card(eSuit.DIAMONDS, eRank.JACK, 1));
		h.AddToCardsInHand(new Card(eSuit.DIAMONDS, eRank.ACE, 1));
		h.AddToCardsInHand(new Card(eSuit.Joker, eRank.Joker, 1));
		h.AddToCardsInHand(new Card(eSuit.Joker, eRank.Joker, 1));
		ArrayList<Hand> AllHands = new ArrayList<Hand>();
		AllHands = Hand.getExplodeHands(h);
		Hand BestHand = new Hand();
		BestHand = Hand.PickBestHand(AllHands);
		try {
			BestHand = Hand.EvaluateHand(BestHand);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(BestHand.getHs().getHandStrength() == eHandStrength.RoyalFlush.getHandStrength());
		assertTrue(BestHand.getHs().getHiHand() == eRank.ACE.getiRankNbr());
	}

	@Test
	public void TestRoyalFlush() {
		Hand h = new Hand();
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.QUEEN, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.JACK, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.KING, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.ACE, 1));

		try {
			h = Hand.EvaluateHand(h);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertTrue(h.getHs().getHandStrength() == eHandStrength.RoyalFlush.getHandStrength());
		assertTrue(h.getHs().getHiHand() == eRank.ACE.getiRankNbr());

	}

	@Test
	public void TestStraightFlush() {
		Hand h = new Hand();
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.JACK, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.QUEEN, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.KING, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.NINE, 1));

		try {
			h = Hand.EvaluateHand(h);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertTrue(h.getHs().getHandStrength() == eHandStrength.StraightFlush.getHandStrength());
		assertTrue(h.getHs().getHiHand() == eRank.KING.getiRankNbr());
	}

	@Test
	public void TestFiveOfAKind() {
		Hand h = new Hand();
		h.AddToCardsInHand(new Card(eSuit.Joker, eRank.Joker, 1));
		h.AddToCardsInHand(new Card(eSuit.DIAMONDS, eRank.NINE, 1));
		h.AddToCardsInHand(new Card(eSuit.SPADES, eRank.NINE, 1));
		h.AddToCardsInHand(new Card(eSuit.HEARTS, eRank.NINE, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.NINE, 1));

		try {
			h = Hand.EvaluateHand(h);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertTrue(h.getHs().getHandStrength() == eHandStrength.FiveOfAKind.getHandStrength());
		assertTrue(h.getHs().getHiHand() == eRank.Joker.getiRankNbr());
	}

	@Test
	public void TestFourOfAKind1() {
		Hand h = new Hand();
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.ACE, 1));

		try {
			h = Hand.EvaluateHand(h);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertTrue(h.getHs().getHandStrength() == eHandStrength.FourOfAKind.getHandStrength());
		assertTrue(h.getHs().getHiHand() == eRank.TEN.getiRankNbr());
		assertTrue(h.getHs().getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit() == eSuit.CLUBS);
		assertEquals(h.getHs().getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.ACE);
	}

	@Test
	public void TestFourOfAKind2() {
		Hand h = new Hand();
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.ACE, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.ACE, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.ACE, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.ACE, 1));

		try {
			h = Hand.EvaluateHand(h);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertTrue(h.getHs().getHandStrength() == eHandStrength.FourOfAKind.getHandStrength());
		assertTrue(h.getHs().getHiHand() == eRank.ACE.getiRankNbr());
		assertTrue(h.getHs().getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit() == eSuit.CLUBS);
		assertEquals(h.getHs().getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.TEN);
	}

	@Test
	public void TestFullHouse1() {
		Hand h = new Hand();
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.ACE, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.ACE, 1));

		try {
			h = Hand.EvaluateHand(h);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertTrue(h.getHs().getHandStrength() == eHandStrength.FullHouse.getHandStrength());
		assertTrue(h.getHs().getHiHand() == eRank.TEN.getiRankNbr());
		assertTrue(h.getHs().getLoHand() == eRank.ACE.getiRankNbr());

	}

	@Test
	public void TestFullHouse2() {
		Hand h = new Hand();
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.ACE, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.ACE, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.ACE, 1));

		try {
			h = Hand.EvaluateHand(h);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertTrue(h.getHs().getHandStrength() == eHandStrength.FullHouse.getHandStrength());
		assertTrue(h.getHs().getHiHand() == eRank.ACE.getiRankNbr());
		assertTrue(h.getHs().getLoHand() == eRank.TEN.getiRankNbr());

	}

	@Test
	public void TestFlush1() {
		Hand h = new Hand();
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.TWO, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.FOUR, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.SIX, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.EIGHT, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.ACE, 1));

		try {
			h = Hand.EvaluateHand(h);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertTrue(h.getHs().getHandStrength() == eHandStrength.Flush.getHandStrength());
		assertTrue(h.getHs().getHiHand() == eRank.ACE.getiRankNbr());
		assertTrue(h.getHs().getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit() == eSuit.CLUBS);
		assertTrue(h.getHs().getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank() == eRank.EIGHT);
		assertTrue(h.getHs().getKickers().get(eCardNo.SecondCard.getCardNo()).geteSuit() == eSuit.CLUBS);
		assertTrue(h.getHs().getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank() == eRank.SIX);
		assertTrue(h.getHs().getKickers().get(eCardNo.ThirdCard.getCardNo()).geteSuit() == eSuit.CLUBS);
		assertTrue(h.getHs().getKickers().get(eCardNo.ThirdCard.getCardNo()).geteRank() == eRank.FOUR);
		assertTrue(h.getHs().getKickers().get(eCardNo.FourthCard.getCardNo()).geteSuit() == eSuit.CLUBS);
		assertTrue(h.getHs().getKickers().get(eCardNo.FourthCard.getCardNo()).geteRank() == eRank.TWO);

	}

	@Test
	public void TestStraight1() {
		Hand h = new Hand();
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.NINE, 1));
		h.AddToCardsInHand(new Card(eSuit.DIAMONDS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.JACK, 1));
		h.AddToCardsInHand(new Card(eSuit.DIAMONDS, eRank.QUEEN, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.KING, 1));

		try {
			h = Hand.EvaluateHand(h);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertTrue(h.getHs().getHandStrength() == eHandStrength.Straight.getHandStrength());
		assertTrue(h.getHs().getHiHand() == eRank.KING.getiRankNbr());
	}

	@Test
	public void TestStraight2() {
		Hand h = new Hand();
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.ACE, 1));
		h.AddToCardsInHand(new Card(eSuit.DIAMONDS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.JACK, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.QUEEN, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.KING, 1));

		try {
			h = Hand.EvaluateHand(h);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertTrue(h.getHs().getHandStrength() == eHandStrength.Straight.getHandStrength());
		assertTrue(h.getHs().getHiHand() == eRank.ACE.getiRankNbr());
	}

	@Test
	public void TestStraight3() {
		Hand h = new Hand();
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.ACE, 1));
		h.AddToCardsInHand(new Card(eSuit.DIAMONDS, eRank.TWO, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.THREE, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.FOUR, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.FIVE, 1));

		try {
			h = Hand.EvaluateHand(h);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertTrue(h.getHs().getHandStrength() == eHandStrength.Straight.getHandStrength());
		assertTrue(h.getHs().getHiHand() == eRank.FIVE.getiRankNbr());
	}

	@Test
	public void TestThreeOfAKind() {
		Hand h = new Hand();
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.DIAMONDS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.SPADES, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.NINE, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.ACE, 1));

		try {
			h = Hand.EvaluateHand(h);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertTrue(h.getHs().getHandStrength() == eHandStrength.ThreeOfAKind.getHandStrength());
		assertTrue(h.getHs().getHiHand() == eRank.TEN.getiRankNbr());
		assertTrue(h.getHs().getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit() == eSuit.CLUBS);
		assertTrue(h.getHs().getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank() == eRank.ACE);
		assertTrue(h.getHs().getKickers().get(eCardNo.SecondCard.getCardNo()).geteSuit() == eSuit.CLUBS);
		assertTrue(h.getHs().getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank() == eRank.NINE);
	}

	@Test
	public void TestThreeOfAKind2() {
		Hand h = new Hand();
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.DIAMONDS, eRank.NINE, 1));
		h.AddToCardsInHand(new Card(eSuit.SPADES, eRank.NINE, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.NINE, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.ACE, 1));

		try {
			h = Hand.EvaluateHand(h);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertTrue(h.getHs().getHandStrength() == eHandStrength.ThreeOfAKind.getHandStrength());
		assertTrue(h.getHs().getHiHand() == eRank.NINE.getiRankNbr());
		assertTrue(h.getHs().getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit() == eSuit.CLUBS);
		assertTrue(h.getHs().getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank() == eRank.ACE);
		assertTrue(h.getHs().getKickers().get(eCardNo.SecondCard.getCardNo()).geteSuit() == eSuit.CLUBS);
		assertTrue(h.getHs().getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank() == eRank.TEN);
	}

	@Test
	public void TestThreeOfAKind3() {
		Hand h = new Hand();
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.NINE, 1));
		h.AddToCardsInHand(new Card(eSuit.DIAMONDS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.SPADES, eRank.ACE, 1));
		h.AddToCardsInHand(new Card(eSuit.HEARTS, eRank.ACE, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.ACE, 1));

		try {
			h = Hand.EvaluateHand(h);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertTrue(h.getHs().getHandStrength() == eHandStrength.ThreeOfAKind.getHandStrength());
		assertTrue(h.getHs().getHiHand() == eRank.ACE.getiRankNbr());
		assertTrue(h.getHs().getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit() == eSuit.DIAMONDS);
		assertTrue(h.getHs().getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank() == eRank.TEN);
		assertTrue(h.getHs().getKickers().get(eCardNo.SecondCard.getCardNo()).geteSuit() == eSuit.CLUBS);
		assertTrue(h.getHs().getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank() == eRank.NINE);
	}

	@Test
	public void TestTwoPair() {
		Hand h = new Hand();
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.DIAMONDS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.SPADES, eRank.NINE, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.NINE, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.ACE, 1));

		try {
			h = Hand.EvaluateHand(h);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertTrue(h.getHs().getHandStrength() == eHandStrength.TwoPair.getHandStrength());
		assertTrue(h.getHs().getHiHand() == eRank.TEN.getiRankNbr());
		assertTrue(h.getHs().getLoHand() == eRank.NINE.getiRankNbr());
		assertTrue(h.getHs().getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit() == eSuit.CLUBS);
		assertTrue(h.getHs().getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank() == eRank.ACE);
	}

	@Test
	public void TestTwoPair2() {
		Hand h = new Hand();
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.DIAMONDS, eRank.ACE, 1));
		h.AddToCardsInHand(new Card(eSuit.SPADES, eRank.NINE, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.NINE, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.ACE, 1));

		try {
			h = Hand.EvaluateHand(h);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertTrue(h.getHs().getHandStrength() == eHandStrength.TwoPair.getHandStrength());
		assertTrue(h.getHs().getHiHand() == eRank.ACE.getiRankNbr());
		assertTrue(h.getHs().getLoHand() == eRank.NINE.getiRankNbr());
		assertTrue(h.getHs().getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit() == eSuit.CLUBS);
		assertTrue(h.getHs().getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank() == eRank.TEN);
	}

	@Test
	public void TestTwoPair3() {
		Hand h = new Hand();
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.DIAMONDS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.SPADES, eRank.ACE, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.NINE, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.ACE, 1));

		try {
			h = Hand.EvaluateHand(h);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertTrue(h.getHs().getHandStrength() == eHandStrength.TwoPair.getHandStrength());
		assertTrue(h.getHs().getHiHand() == eRank.ACE.getiRankNbr());
		assertTrue(h.getHs().getLoHand() == eRank.TEN.getiRankNbr());
		assertTrue(h.getHs().getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit() == eSuit.CLUBS);
		assertTrue(h.getHs().getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank() == eRank.NINE);
	}

	@Test
	public void TestOnePair1() {
		Hand h = new Hand();
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.DIAMONDS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.SPADES, eRank.NINE, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.EIGHT, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.ACE, 1));

		try {
			h = Hand.EvaluateHand(h);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertTrue(h.getHs().getHandStrength() == eHandStrength.Pair.getHandStrength());
		assertTrue(h.getHs().getHiHand() == eRank.TEN.getiRankNbr());
		assertTrue(h.getHs().getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit() == eSuit.CLUBS);
		assertTrue(h.getHs().getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank() == eRank.ACE);
		assertTrue(h.getHs().getKickers().get(eCardNo.SecondCard.getCardNo()).geteSuit() == eSuit.SPADES);
		assertTrue(h.getHs().getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank() == eRank.NINE);
		assertTrue(h.getHs().getKickers().get(eCardNo.ThirdCard.getCardNo()).geteSuit() == eSuit.CLUBS);
		assertTrue(h.getHs().getKickers().get(eCardNo.ThirdCard.getCardNo()).geteRank() == eRank.EIGHT);
	}

	@Test
	public void TestOnePair2() {
		Hand h = new Hand();
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.DIAMONDS, eRank.NINE, 1));
		h.AddToCardsInHand(new Card(eSuit.SPADES, eRank.NINE, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.EIGHT, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.ACE, 1));

		try {
			h = Hand.EvaluateHand(h);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertTrue(h.getHs().getHandStrength() == eHandStrength.Pair.getHandStrength());
		assertTrue(h.getHs().getHiHand() == eRank.NINE.getiRankNbr());
		assertTrue(h.getHs().getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit() == eSuit.CLUBS);
		assertTrue(h.getHs().getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank() == eRank.ACE);
		assertTrue(h.getHs().getKickers().get(eCardNo.SecondCard.getCardNo()).geteSuit() == eSuit.CLUBS);
		assertTrue(h.getHs().getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank() == eRank.TEN);
		assertTrue(h.getHs().getKickers().get(eCardNo.ThirdCard.getCardNo()).geteSuit() == eSuit.CLUBS);
		assertTrue(h.getHs().getKickers().get(eCardNo.ThirdCard.getCardNo()).geteRank() == eRank.EIGHT);
	}

	@Test
	public void TestOnePair3() {
		Hand h = new Hand();
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.DIAMONDS, eRank.NINE, 1));
		h.AddToCardsInHand(new Card(eSuit.SPADES, eRank.EIGHT, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.EIGHT, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.ACE, 1));

		try {
			h = Hand.EvaluateHand(h);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertTrue(h.getHs().getHandStrength() == eHandStrength.Pair.getHandStrength());
		assertTrue(h.getHs().getHiHand() == eRank.EIGHT.getiRankNbr());
		assertTrue(h.getHs().getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit() == eSuit.CLUBS);
		assertTrue(h.getHs().getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank() == eRank.ACE);
		assertTrue(h.getHs().getKickers().get(eCardNo.SecondCard.getCardNo()).geteSuit() == eSuit.CLUBS);
		assertTrue(h.getHs().getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank() == eRank.TEN);
		assertTrue(h.getHs().getKickers().get(eCardNo.ThirdCard.getCardNo()).geteSuit() == eSuit.DIAMONDS);
		assertTrue(h.getHs().getKickers().get(eCardNo.ThirdCard.getCardNo()).geteRank() == eRank.NINE);
	}

	@Test
	public void TestOnePair4() {
		Hand h = new Hand();
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.DIAMONDS, eRank.EIGHT, 1));
		h.AddToCardsInHand(new Card(eSuit.SPADES, eRank.NINE, 1));
		h.AddToCardsInHand(new Card(eSuit.HEARTS, eRank.ACE, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.ACE, 1));

		try {
			h = Hand.EvaluateHand(h);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertTrue(h.getHs().getHandStrength() == eHandStrength.Pair.getHandStrength());
		assertTrue(h.getHs().getHiHand() == eRank.ACE.getiRankNbr());
		assertTrue(h.getHs().getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit() == eSuit.CLUBS);
		assertTrue(h.getHs().getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank() == eRank.TEN);
		assertTrue(h.getHs().getKickers().get(eCardNo.SecondCard.getCardNo()).geteSuit() == eSuit.SPADES);
		assertTrue(h.getHs().getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank() == eRank.NINE);
		assertTrue(h.getHs().getKickers().get(eCardNo.ThirdCard.getCardNo()).geteSuit() == eSuit.DIAMONDS);
		assertTrue(h.getHs().getKickers().get(eCardNo.ThirdCard.getCardNo()).geteRank() == eRank.EIGHT);
	}

	@Test
	public void TestHighCard() {
		Hand h = new Hand();
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.TEN, 1));
		h.AddToCardsInHand(new Card(eSuit.DIAMONDS, eRank.TWO, 1));
		h.AddToCardsInHand(new Card(eSuit.SPADES, eRank.NINE, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.EIGHT, 1));
		h.AddToCardsInHand(new Card(eSuit.CLUBS, eRank.ACE, 1));

		try {
			h = Hand.EvaluateHand(h);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertTrue(h.getHs().getHandStrength() == eHandStrength.HighCard.getHandStrength());
		assertTrue(h.getHs().getHiHand() == eRank.ACE.getiRankNbr());
		assertTrue(h.getHs().getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit() == eSuit.CLUBS);
		assertTrue(h.getHs().getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank() == eRank.TEN);
		assertTrue(h.getHs().getKickers().get(eCardNo.SecondCard.getCardNo()).geteSuit() == eSuit.SPADES);
		assertTrue(h.getHs().getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank() == eRank.NINE);
		assertTrue(h.getHs().getKickers().get(eCardNo.ThirdCard.getCardNo()).geteSuit() == eSuit.CLUBS);
		assertTrue(h.getHs().getKickers().get(eCardNo.ThirdCard.getCardNo()).geteRank() == eRank.EIGHT);
		assertTrue(h.getHs().getKickers().get(eCardNo.FourthCard.getCardNo()).geteSuit() == eSuit.DIAMONDS);
		assertTrue(h.getHs().getKickers().get(eCardNo.FourthCard.getCardNo()).geteRank() == eRank.TWO);
	}

}