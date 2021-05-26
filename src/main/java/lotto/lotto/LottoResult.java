package lotto.lotto;

import lotto.enums.Prize;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private final Map<Prize, Integer> result = new HashMap<>();

    public int count(Prize prize) {
        return getOrDefault(prize);
    }

    public void increaseCount(int matchingCount, boolean matchBonus) {
        if (matchingCount < Prize.FIFTH.matchingCount()) {
            return;
        }
        Prize prize = Prize.valueOf(matchingCount, matchBonus);
        int count = getOrDefault(prize);
        result.put(prize, ++count);
    }

    public int income() {
        int total = 0;
        for (Prize prizeValue : Prize.values()) {
            total += prizeValue.income(this);
        }
        return total;
    }

    private int getOrDefault(Prize prize) {
        return result.getOrDefault(prize, 0);
    }
}
