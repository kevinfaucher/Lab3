package pokerBase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import pokerEnums.eHandStrength;
import pokerEnums.eRank;
import pokerEnums.eSuit;
import pokerExceptions.DeckException;


public class Deck implements Serializable  {

	/**
	 * 
	 */
	private ArrayList<Card> deckCards = new ArrayList<Card>();

	/**
	 * No arg constructor for deck, will return shuffled deck of 52 cards
	 */
	public Deck() {
		int iCardNbr = 1;
		for (eSuit eSuit : eSuit.values()) {
			for (eRank eRank : eRank.values()) {
				if ((eRank != eRank.Joker) && (eSuit != eSuit.Joker))
					deckCards.add(new Card(eSuit, eRank, iCardNbr++));
			}
		}
		Collections.shuffle(deckCards);
	}

	public Deck(int iNbrOfJokers) {
		this();

		for (int i = 0; i < iNbrOfJokers; i++) {
			deckCards.add(new Card(eSuit.Joker, eRank.Joker, 53));
		}
		Collections.shuffle(deckCards);
	}



	 Deck(eSuit suit) {
		int iCardNbr = 1;
		for (eSuit eSuit : eSuit.values()) {
			for (eRank eRank : eRank.values()) {
				if ((eRank != eRank.Joker) && (eSuit != eSuit.Joker))
					if (suit == eSuit) {
						deckCards.add(new Card(eSuit, eRank, iCardNbr));
					}
				iCardNbr++;
			}
		}
		Collections.shuffle(deckCards);
	}

	ArrayList<Card> getDeckCards() {
		return deckCards;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pokerBase.iDeck#Draw()
	 */
	public Card Draw() throws DeckException {
		if (deckCards.size() == 0) {
			throw new DeckException(this);
		}
		return deckCards.remove(0);
	}

	/**
	 * Returns the number of cards left in the deck
	 * 
	 * @return
	 */
	private int GetDeckSize() {
		return deckCards.size();
	}
}