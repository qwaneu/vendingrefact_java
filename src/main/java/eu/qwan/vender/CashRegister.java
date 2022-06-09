package eu.qwan.vender;

import java.util.Map;

public class CashRegister implements Cashier {

    @Override
    public Can purchase(VendingMachine vendingMachine, CanContainer canContainer) {
        if (canContainer.getPrice() <= vendingMachine.credit && canContainer.getAmount() > 0) {
            vendingMachine.credit -= canContainer.getPrice();
            canContainer.setAmount(canContainer.getAmount() - 1);
            return canContainer.getType();
        }
        return Can.NONE;
    }

}
