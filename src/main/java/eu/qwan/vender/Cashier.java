package eu.qwan.vender;

import java.util.Map;

public interface Cashier {
    Can purchase(VendingMachine vendingMachine, CanContainer canContainer);
}
