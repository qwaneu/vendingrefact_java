package eu.qwan.vender;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
	public final Map<Choice, CanContainer> cans = new HashMap<>();
	private Cashier cashier = new CashRegister();
	public Card card;
	public int credit;

	public void setValue(int value) {
		cashier = new CashRegister();
		credit += value;
	}

	public void insertCard(Card card) {
		cashier = new CardRegister();
		this.card = card;
	}

	public Can deliver(Choice choice) {
		if (cans.containsKey(choice))
			return cashier.purchase(this, cans.get(choice));
		else
			return Can.NONE;
	}

	public int getChange() {
		int result = credit;
		if (credit > 0) {
			credit = 0;
			return result;
		}
		return 0;
	}

	public void configure(Choice choice, Can can, int amount) {
		configure(choice, can, amount, 0);
	}

	public void configure(Choice choice, Can can, int amount, int price) {
		if (cans.containsKey(choice)) {
			cans.get(choice).setAmount(cans.get(choice).getAmount() + amount);
		} else {
			CanContainer canContainer = new CanContainer();
			canContainer.setType(can);
			canContainer.setAmount(amount);
			canContainer.setPrice(price);
			cans.put(choice, canContainer);
		}
	}
}
