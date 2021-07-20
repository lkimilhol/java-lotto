package lotto.shop;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.Quantity;
import lotto.enums.Prize;
import lotto.lotto.WinningNumber;
import lotto.lotto.LottoNumber;
import lotto.error.ErrorMessage;
import lotto.lotto.Lotto;
import lotto.lotto.LottoTicket;
import lotto.lotto.LottoResult;

public class ShopTest {
    Shop shop;

    @BeforeEach
    void setup() {
        shop = new Shop();
    }

    @Test
    @DisplayName("구매 금액에 맞는 로또 구입 횟수")
    void buyLottoCount() {
        //given
        Money 초기_금액 = Money.of(10000);
        //when
        Quantity 로또_자동_개수 = shop.buyAutoLotto(초기_금액);
        LottoTicket 로또_티켓 = new LottoTicket(로또_자동_개수);
        //then
        assertThat(로또_티켓.quantity()).isEqualTo(10);
    }

    @Test
    @DisplayName("구매 금액이 부족한 경우")
    void buyFailed() {
        //given
        Money 초기_금액 = Money.of(900);
        //when
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> shop.buyAutoLotto(초기_금액)).withMessageContaining(ErrorMessage.NOT_ENOUGH_MONEY);
        //then
    }

    @Test
    @DisplayName("구매한 만큼 로또 구입")
    void buyLotto() {
        //given
        Money 초기_금액 = Money.of(10000);
        //when
        Quantity 로또_자동_개수 = shop.buyAutoLotto(초기_금액);
        //then
        assertThat(로또_자동_개수.getAmount()).isEqualTo(10);
    }

    @Test
    @DisplayName("로또 오토")
    void makeLottoAuto() {
        //given
        Money 초기_금액 = Money.of(10000);
        //when
        Quantity 로또_자동_개수 = shop.buyAutoLotto(초기_금액);
        LottoTicket 로또_티켓 = new LottoTicket(로또_자동_개수);
        //then
        assertThat(로또_티켓.quantity()).isEqualTo(10);
    }

    @Test
    @DisplayName("번호가 맞는지")
    void testAnswer() {
        //given
        Quantity 자동_로또_개수 = new Quantity(0);
        WinningNumber 당첨_번호 = WinningNumber.of(1, 2, 3, 4, 5, 6);

        Lotto 수동_로또 = Lotto.of(1, 2, 3, 10, 11, 12);
        LottoNumber 보너스_번호 = LottoNumber.of(45);
        LottoTicket 로또_티켓 = new LottoTicket(자동_로또_개수, 수동_로또);
        //when
        LottoResult 결과 = 로또_티켓.matchWinningNumber(당첨_번호, 보너스_번호);
        //then
        assertThat(결과.count(Prize.FIFTH)).isEqualTo(1);
    }

    @Test
    @DisplayName("2등 당첨")
    void testSecond() {
        //given
        Quantity 자동_로또_개수 = new Quantity(0);
        WinningNumber 당첨_번호 = WinningNumber.of(1, 2, 3, 4, 5, 6);

        Lotto 내가_선택한_번호 = Lotto.of(1, 2, 3, 4, 5, 16);
        LottoTicket 로또_티켓 = new LottoTicket(자동_로또_개수, 내가_선택한_번호);
        LottoNumber 보너스_번호 = LottoNumber.of(16);
        //when
        LottoResult 결과 = 로또_티켓.matchWinningNumber(당첨_번호, 보너스_번호);

        //then
        assertThat(결과.count(Prize.SECOND)).isEqualTo(1);
    }

    @Test
    @DisplayName("보너스 넘버가 중복 될 경우 에러")
    void checkDuplicateBonusNumber() {
        //given
        Quantity 자동_로또_개수 = new Quantity(0);
        WinningNumber 당첨_번호 = WinningNumber.of(1, 2, 3, 4, 5, 6);

        LottoNumber 보너스_번호 = LottoNumber.of(1);
        LottoTicket 빈_로또_티켓 = new LottoTicket(자동_로또_개수);
        //when
        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> 빈_로또_티켓.matchWinningNumber(당첨_번호, 보너스_번호))
                .withMessageContaining(ErrorMessage.DUPLICATE_BONUS_NUMBER);
    }

    @Test
    @DisplayName("셀프 로또 구매 확인")
    void buySelfLottoAmount() {
        //given
        Money 초기_금액 = shop.buySelfLotto(Money.of(10000), new Quantity(7));
        //then
        assertThat(초기_금액.amount()).isEqualTo(3000);
    }

    @Test
    @DisplayName("셀프 로또 금액 부족")
    void buySelfLottoFailed() {
        //given
        //when
        //then
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> shop.buySelfLotto(Money.of(10000), new Quantity(100)))
                .withMessageContaining(ErrorMessage.NOT_ENOUGH_MONEY);
    }

    @Test
    @DisplayName("셀프 구매후 자동 구매")
    void buySelfAutoLotto() {
        //given
        //when
        Money 남은_금액 = shop.buySelfLotto(Money.of(2000), new Quantity(1));
        Quantity 자동_횟수 = shop.buyAutoLotto(남은_금액);
        Lotto 로또_수동 = Lotto.of(1, 2, 3, 4, 5, 6);
        LottoTicket 로또_티켓 = new LottoTicket(자동_횟수, 로또_수동);
        //then
        assertThat(로또_티켓.tickets().size()).isEqualTo(2);
    }
}