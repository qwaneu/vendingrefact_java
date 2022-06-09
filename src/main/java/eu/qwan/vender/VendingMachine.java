package eu.qwan.vender;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
	private final Map<Choice, CanContainer> cans = new HashMap<>();
	private int paymentMethod;
	private Chipknip chipknip;
	private int credit = -1;

	public void setValue(int v) {
		paymentMethod = 1;
		if (credit != -1) {
			credit += v;
		} else {
			credit = v;
		}
	}

	public void insertChip(Chipknip chipknip) {
		// TODO
		// can't pay with chip in brittain
		paymentMethod = 2;
		this.chipknip = chipknip;
	}

	// delivers the can if all ok {
	public Can deliver(Choice choice) {
		Can res = Can.none;
		//
		// step 1: check if choice exists {
		//
		if (cans.containsKey(choice)) {
			//
			// step2 : check price
			//
			if (cans.get(choice).price == 0) {
				res = cans.get(choice).getType();
				// or price matches
			} else {

				switch (paymentMethod) {
				case 1: // paying with coins
					if (credit != -1 && cans.get(choice).price <= credit) {
						res = cans.get(choice).getType();
						credit -= cans.get(choice).price;
					}
					break;
				case 2: // paying with chipknip -
					// TODO: if this machine is in belgium this must be an error
					// {
					if (chipknip.hasValue(cans.get(choice).price)) {
						chipknip.reduce(cans.get(choice).price);
						res = cans.get(choice).getType();
					}
					break;
				default:
					// TODO: Is this a valid situation?:
					// larry forgot the } else { clause
					// i added it, but i am acutally not sure as to wether this
					// is a problem
					// unknown payment
					break;
				// i think(i) nobody inserted anything
				}
			}
		}

		//
		// step 3: check stock
		//
		if (res != Can.none) {
			if (cans.get(choice).getAmount() <= 0) {
				res = Can.none;
			} else {
				cans.get(choice).setAmount(cans.get(choice).getAmount() - 1);
			}
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
