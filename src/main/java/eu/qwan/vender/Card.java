package eu.qwan.vender;

public class Card {
	public int credits;

	public Card(int initialValue) {
		credits = initialValue;
	}

	public boolean hasValue(int price) {
		return credits >= price;
	}

	public void reduce(int price) {
		credits -= price;
	}
}
