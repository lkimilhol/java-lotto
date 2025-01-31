package lotto.ui;

import java.util.stream.Collectors;

import lotto.Quantity;
import lotto.enums.Message;
import lotto.enums.Prize;
import lotto.lotto.Lotto;
import lotto.lotto.LottoNumber;
import lotto.lotto.LottoTicket;
import lotto.lotto.LottoResult;
import lotto.shop.Money;

public class OutputView {
    public static void printInputMoneyMessage() {
        println(Message.INPUT_MONEY.message());
    }

    public static void printWinningNumber() {
        println(Message.INPUT_LOTTO_ANSWER.message());
    }

    public static void printLottoTicket(LottoTicket lottoTicket) {
        for(Lotto lotto : lottoTicket.tickets()) {
            printLotto(lotto);
        }
    }

    public static void printInputSelfLottoQuantity() {
        println(Message.INPUT_SELF_LOTTO_AMOUNT.message());
    }

    public static void printInputSelfLottoNumber() {
        println(Message.INPUT_SELF_LOTTO_NUMBERS.message());
    }

    public static void resultMessage(LottoResult lottoResult, Money money) {
        println(Message.RESULT.message());
        println(Message.LINE.message());
        OutputView.resultIncome(lottoResult, money);
    }

    public static void resultIncome(LottoResult lottoResult, Money money) {
        for (Prize prizeValue : Prize.values()) {
            printLottoResult(prizeValue, prizeValue.money(), lottoResult);
        }
        printIncome(lottoResult, money);
    }

    public static void printBonusNumber() {
        println(Message.INPUT_BONUS_NUMBER.message());
    }

    public static void printLottoQuantity(Quantity self, int auto) {
        System.out.printf("수동으로 %d장, 자동으로  %d 개를 구매했습니다", self.getAmount(), auto);
        System.out.println();
    }

    private static void printLotto(Lotto lotto) {
        String collect = lottoNumberToString(lotto);
        println("[" + collect + "]");
    }

    private static void println(String message) {
        System.out.println(message);
    }

    private static void printLottoResult(Prize prize, int money, LottoResult matchAnswer) {
        String bonusInfo = "";
        if (prize.isSecond()) {
            bonusInfo = Message.BONUS_BALL_MESSAGE.message();
        }
        System.out.printf("%d개 일치" + bonusInfo + "(%d원)- %d개\n", prize.matchingCount(), money, matchAnswer.count(prize));
    }

    private static void printIncome(LottoResult lottoResult, Money money) {
        System.out.printf(Message.INCOME_PREFIX.message() + "%.2f" + Message.INCOME_POSTFIX.message() + "%n", (float) lottoResult.income() / money.amount());
    }

    private static String lottoNumberToString(Lotto lotto) {
        return lotto.numbers().stream()
                .map(LottoNumber::getNumber)
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(InputView.NUMBER_DELIMITER));
    }
}