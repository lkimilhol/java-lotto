package lotto.shop;

import lotto.error.ErrorMessage;
import lotto.lotto.WinningNumber;
import lotto.lotto.Lotto;
import lotto.lotto.LottoNumber;
import lotto.lotto.LottoTicket;
import lotto.lotto.MatchedAnswer;

import java.util.Random;
import java.util.stream.IntStream;

public class Shop {
    private static final int PURCHASE_PRICE = 1000;

    public int buyLotto(Money money) {
        checkMoney(money);
        return money.amount() / PURCHASE_PRICE;
    }

    public LottoTicket selectAuto(int amount) {
        LottoTicket lottoTicket = new LottoTicket();
        for (int i = 0; i < amount; i++) {
            lottoTicket.add(createAutoLotto());
        }
        return lottoTicket;
    }

    public MatchedAnswer matchAnswer(LottoTicket lottoTicket, WinningNumber winningNumber) {
        MatchedAnswer match = new MatchedAnswer();
        lottoTicket.tickets().forEach(lotto -> {
            lotto.retainAll(winningNumber);
            match.increaseCount(lotto.answerCount());
        });
        return match;
    }

    private void checkMoney(Money money) {
        if (money.amount() < PURCHASE_PRICE) {
            throw new RuntimeException(ErrorMessage.NOT_ENOUGH_MONEY);
        }
    }

    private Lotto createAutoLotto() {
        return generateNumber();
    }

    private Lotto generateNumber() {
        Random random = new Random();
        int[] numbers = IntStream.generate(() -> random.nextInt(LottoNumber.MAX_NUMBER) + LottoNumber.MIN_NUMBER)
                .distinct()
                .limit(Lotto.MAX_COUNT)
                .boxed()
                .mapToInt(i -> i)
                .toArray();

        return new Lotto(numbers);
    }
}