package eu.qwan.vender;

public class CardRegister implements Cashier {

    private Card card;

    public CardRegister(Card card) {
        this.card = card;
    }

    @Override
    public Can purchase(CanContainer canContainer) {
        if (card.hasValue(canContainer.getPrice())) {
            card.reduce(canContainer.getPrice());
            canContainer.setAmount(canContainer.getAmount() - 1);
            return canContainer.getType();
        }
        return Can.NONE;
    }

}
