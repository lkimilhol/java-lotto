package lotto.shop;

import lotto.Quantity;
import lotto.error.ErrorMessage;

public class Shop {
    private static final Money LOTTO_PRICE_MONEY = Money.of(1000);

    public Money buySelfLotto(Money ownMoney, Quantity lottoQuantity) {
        checkMoney(ownMoney);
        return purchase(ownMoney, lottoQuantity);
    }

    public Quantity buyAutoLotto(Money money) {
        checkMoney(money);
        return new Quantity(money.getQuantity(LOTTO_PRICE_MONEY));
    }

    private Money purchase(Money ownMoney, Quantity quantity) {
        return calculate(ownMoney, quantity);
    }

    private void checkMoney(Money money) {
        if (money.lt(LOTTO_PRICE_MONEY)) {
            throw new RuntimeException(ErrorMessage.NOT_ENOUGH_MONEY);
        }
    }

    private void checkMoney(Money money, Quantity quantity) {
        if (money.lt(LOTTO_PRICE_MONEY, quantity)) {
            throw new RuntimeException(ErrorMessage.NOT_ENOUGH_MONEY);
        }
    }

    private Money calculate(Money money, Quantity quantity) {
        checkMoney(money, quantity);
        return money.sub(LOTTO_PRICE_MONEY.multiplyQuantity(quantity));
    }
}