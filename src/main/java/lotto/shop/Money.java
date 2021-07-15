package lotto.shop;

import java.util.Objects;

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

    public boolean lt(Money money) {
        return amount < money.amount;
    }

    public boolean lt(Money lottoPriceMoney, int quantity) {
        return amount < lottoPriceMoney.amount * quantity;
    }

    public int amount() {
        return amount;
    }

    public Money sub(Money money) {
        return new Money(amount - money.amount);
    }

    public Money multiplyQuantity(int quantity) {
        return new Money(amount * quantity);
    }

    public int getQuantity(Money money) {
        checkZero(money.amount);
        return amount / money.amount;
    }

    private void checkNegative(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(ErrorMessage.MONEY_CANNOT_BE_NEGATIVE);
        }
    }

    private void checkZero(int amount) {
        if (amount == 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}