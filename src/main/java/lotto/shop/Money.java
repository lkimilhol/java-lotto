package lotto.shop;

import lotto.error.ErrorMessage;

public class Money {
    private final int amount;

    private Money(int amount) {
        checkNegative(amount);
        this.amount = amount;
    }

    public static Money of(int amount) {
        return new Money(amount);
    }

    public int amount() {
        return amount;
    }

    private void checkNegative(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(ErrorMessage.MONEY_CANNOT_BE_NEGATIVE);
        }
    }
}