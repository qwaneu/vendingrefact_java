package eu.qwan.vender;

public class CashRegister implements Cashier {

    private int credit;

    @Override
    public Can purchase(CanContainer canContainer) {
        if (canContainer.getPrice() <= credit) {
            credit -= canContainer.getPrice();
            canContainer.setAmount(canContainer.getAmount() - 1);
            return canContainer.getType();
        }
        return Can.NONE;
    }

    public void insert(int value) {
        credit += value;
    }

    public int getChange() {
        int result = credit;
        if (credit > 0) {
            credit = 0;
            return result;
        }
        return 0;
    }
}
