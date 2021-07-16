package lotto.shop;

import lotto.error.ErrorMessage;

public class Shop {
    private static final Money LOTTO_PRICE_MONEY = Money.of(1000);

    public Money buySelfLotto(Money ownMoney, int lottoQuantity) {
        checkMoney(ownMoney);
        return purchase(ownMoney, lottoQuantity);
    }

    public int buyAutoLotto(Money money) {
        checkMoney(money);
        return money.getQuantity(LOTTO_PRICE_MONEY);
    }

    private Money purchase(Money ownMoney, int quantity) {
        return calculate(ownMoney, quantity);
    }

    private void checkMoney(Money money) {
        if (money.lt(LOTTO_PRICE_MONEY)) {
            throw new RuntimeException(ErrorMessage.NOT_ENOUGH_MONEY);
        }
    }

    private void checkMoney(Money money, int quantity) {
        if (money.lt(LOTTO_PRICE_MONEY, quantity)) {
            throw new RuntimeException(ErrorMessage.NOT_ENOUGH_MONEY);
        }
    }

    private Money calculate(Money money, int quantity) {
        checkMoney(money, quantity);
        return money.sub(LOTTO_PRICE_MONEY.multiplyQuantity(quantity));
    }
}