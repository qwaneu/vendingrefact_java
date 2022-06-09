package eu.qwan.vender;

import java.util.Map;

public class CardRegister implements Cashier {

    @Override
    public Can purchase(VendingMachine vendingMachine, CanContainer canContainer) {
        if (vendingMachine.card.hasValue(canContainer.getPrice()) && canContainer.getAmount() > 0) {
            vendingMachine.card.reduce(canContainer.getPrice());
            canContainer.setAmount(canContainer.getAmount() - 1);
            return canContainer.getType();
        }
        return Can.NONE;
    }

}
