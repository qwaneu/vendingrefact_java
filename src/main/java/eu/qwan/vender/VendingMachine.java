package eu.qwan.vender;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
	private final Map<Choice, CanContainer> cans = new HashMap<>();
	private PaymentMethod paymentMethod = PaymentMethod.CASH;
	private Card card;
	private int credit;

	public void setValue(int value) {
		paymentMethod = PaymentMethod.CASH;
		credit += value;
	}

	public void insertCard(Card card) {
		paymentMethod = PaymentMethod.CARD;
		this.card = card;
	}

	public Can deliver(Choice choice) {
		Can res = Can.none;
		if (!cans.containsKey(choice)) return Can.none;
		switch (paymentMethod) {
			case CASH:
				if (cans.get(choice).price <= credit && cans.get(choice).getAmount() > 0) {
					credit -= cans.get(choice).price;
					cans.get(choice).setAmount(cans.get(choice).getAmount() - 1);
					res = cans.get(choice).getType();
				}
				break;
			case CARD:
				if (card.hasValue(cans.get(choice).price) && cans.get(choice).getAmount() > 0) {
					card.reduce(cans.get(choice).price);
					cans.get(choice).setAmount(cans.get(choice).getAmount() - 1);
					res = cans.get(choice).getType();
				}
				break;
		}
		return res;
	}

	public int getChange() {
		int result = credit;
		if (credit > 0) {
			credit = 0;
			return result;
		}
		return 0;
	}

	public void configure(Choice choice, Can c, int n) {
		configure(choice, c, n, 0);
	}

	public void configure(Choice choice, Can c, int n, int price) {
		if (cans.containsKey(choice)) {
			cans.get(choice).setAmount(cans.get(choice).getAmount() + n);
			return;
		}
		CanContainer can = new CanContainer();
		can.setType(c);
		can.setAmount(n);
		can.setPrice(price);
		cans.put(choice, can);
	}
}
