package lotto;

import java.util.ArrayList;
import java.util.List;

import lotto.lotto.Lotto;
import lotto.shop.NumberGenerator;

public class Quantity {
    private final int amount;

    public Quantity(int amount) {
        checkAmount(amount);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public List<Lotto> markingLotto() {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            lottos.add(new Lotto(NumberGenerator.generate()));
        }
        return lottos;
    }

    private void checkAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException();
        }
    }
}
