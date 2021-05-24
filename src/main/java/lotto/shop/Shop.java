package lotto.shop;

import lotto.error.ErrorMessage;
import lotto.lotto.WinningNumber;
import lotto.lotto.Lotto;
import lotto.lotto.LottoTicket;
import lotto.lotto.LottoResult;

public class Shop {
    private static final int PURCHASE_PRICE = 1000;

    public LottoTicket buyAutoLotto(Money money) {
        checkMoney(money);
        return selectAuto(money.amount() / PURCHASE_PRICE);
    }

    private LottoTicket selectAuto(int amount) {
        LottoTicket lottoTicket = new LottoTicket();
        for (int i = 0; i < amount; i++) {
            lottoTicket.add(createAutoLotto());
        }
        return lottoTicket;
    }

    public LottoResult matchAnswer(LottoTicket lottoTicket, WinningNumber winningNumber) {
        return lottoTicket.matchWinningNumber(winningNumber);
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
        return new Lotto(NumberGenerator.generate());
    }
}