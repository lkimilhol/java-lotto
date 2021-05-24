package lotto.enums;

import lotto.error.ErrorMessage;
import lotto.lotto.LottoResult;

import java.util.Arrays;
import java.util.Optional;

public enum PrizeEnum {
    MISSING(0, 0),
    FIFTH(3, 5000),
    FOURTH(4,50000),
    THIRD(5,1500000),
    SECOND(5,30000000),
    FIRST(6, 2000000000);

    int countOfMatch;
    int matchingCount;
    int prize;

    PrizeEnum(int matchingCount, int prize) {
        this.matchingCount = matchingCount;
        this.prize = prize;
    }

    public int matchingCount() {
        return matchingCount;
    }

    public int prize() {
        return prize;
    }

    public int income(LottoResult matchAnswer) {
        return prize * matchAnswer.count(countOfMatch);
    }

    public static PrizeEnum valueOf(int countOfMatch, boolean matchBonus) {
        PrizeEnum prize = Arrays.stream(values())
                .filter(v -> v.countOfMatch == countOfMatch)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_PRIZE_ENUM));

        if (prize.countOfMatch == SECOND.countOfMatch && matchBonus) {
            return SECOND;
        }

        return prize;
    public int income(LottoResult lottoResult) {
        return prize * lottoResult.count(matchingCount);
    }
}