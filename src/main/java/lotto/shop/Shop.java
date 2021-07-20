package lotto.shop;

import lotto.Quantity;
import lotto.error.ErrorMessage;

public class Shop {
    private static final int PURCHASE_PRICE = 1000;

    public Money buySelfLotto(Money money, Quantity lottoQuantity) {
        checkMoney(money);
        return purchase(money, lottoQuantity);
    }

    public Quantity buyAutoLotto(Money money) {
        checkMoney(money);
        return new Quantity(money.amount() / PURCHASE_PRICE);
    }

    private Money purchase(Money money, Quantity quantity) {
        checkMoney(money);
        return new Money(calculate(money, quantity));
    }

    private void checkMoney(Money money) {
        if (money.amount() < PURCHASE_PRICE) {
            throw new RuntimeException(ErrorMessage.NOT_ENOUGH_MONEY);
        }
    }

    private void checkMoney(Money money, Quantity quantity) {
        if (money.amount() < quantity.getAmount() * PURCHASE_PRICE) {
            throw new RuntimeException(ErrorMessage.NOT_ENOUGH_MONEY);
        }
    }

    private int calculate(Money money, Quantity quantity) {
        checkMoney(money, quantity);
        return money.amount() - quantity.getAmount() * PURCHASE_PRICE;
    }
}