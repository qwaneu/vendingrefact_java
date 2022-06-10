package eu.qwan.vender;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
	public final Map<Choice, CanContainer> cans = new HashMap<>();
	private final CashRegister cashregister = new CashRegister();
	private Cashier cashier = cashregister;
	public Card card;
	public int credit;

	public void setValue(int value) {
		credit += value;
		cashregister.insert(value);
	}

	public void insertCard(Card card) {
		cashier = new CardRegister(card);
		this.card = card;
	}

	public Can deliver(Choice choice) {
		if (cans.containsKey(choice)  && cans.get(choice).getAmount() > 0)
			return cashier.purchase(cans.get(choice));
		else
			return Can.NONE;
	}

	public int getChange() {
		return cashregister.getChange();
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
