package pokerBase;

import java.util.ArrayList;
import java.util.Collections;

import pokerEnums.eRank;
import pokerEnums.eSuit;
import pokerExceptions.DeckException;

public class Deck {

	private ArrayList<Card> deckCards = new ArrayList<Card>();

	public Deck(int nbrOfJokers) {
		int iCardNbr = 1;
		for (eSuit eSuit : eSuit.values()) {
			for (eRank eRank : eRank.values()) {
				if ((eSuit != pokerEnums.eSuit.Joker) && (eRank != pokerEnums.eRank.Joker)) {
					deckCards.add(new Card(eSuit, eRank, iCardNbr++));
				}
			}

		}
		Collections.shuffle(deckCards);
	}

	public Deck(int NbrOfJokers, ArrayList<Card> Wilds) {

		this(NbrOfJokers);

		for (Card dCard : deckCards) {
			for (Card wCard : Wilds) {
				if (dCard.geteSuit() == wCard.geteSuit() && dCard.geteRank() == wCard.geteRank()) {
					dCard.setWild();
				}
			}
		}

	}

	public Card Draw() throws DeckException {
		if (deckCards.size() == 0)
			throw new DeckException(this);
		return deckCards.remove(0);
	}

	public Object getDeckCards() {
		return deckCards;
	}
}
