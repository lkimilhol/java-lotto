package lotto.shop;

import static org.assertj.core.api.Assertions.*;

import lotto.common.ErrorMessage;
import lotto.lotto.Lotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.print.attribute.HashAttributeSet;
import java.util.*;

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
        Money money = new Money(10000);
        //when
        int amount = shop.buyLotto(money);
        //then
        assertThat(amount).isEqualTo(10);
    }

    @Test
    @DisplayName("구매 금액이 부족한 경우")
    void buyFailed() {
        //given
        Money money = new Money(900);
        //when
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> shop.buyLotto(money)).withMessageContaining(ErrorMessage.NOT_ENOUGH_MONEY);
        //then
    }

    @Test
    @DisplayName("구매한 만큼 로또 구입")
    void butLotto() {
        //given
        Money money = new Money(10000);
        //when
        int amount = shop.buyLotto(money);
        //then
        assertThat(amount).isEqualTo(10);
    }

    @Test
    @DisplayName("로또 오토")
    void makeLottoAuto() {
        //given
        Money money = new Money(10000);
        //when
        int amount = shop.buyLotto(money);
        List<Lotto> lottoBundle = shop.selectAuto(amount);
        //then
        assertThat(lottoBundle.size()).isEqualTo(10);
    }

    @Test
    @DisplayName("번호가 맞는지")
    void testAnswer() {
        //given
        List<Lotto> lottoBundle = new ArrayList<>();
        Set<Integer> answer = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));

        Lotto lotto = new Lotto();
        lotto.addNumber(1);
        lotto.addNumber(2);
        lotto.addNumber(3);
        lotto.addNumber(10);
        lotto.addNumber(11);
        lotto.addNumber(12);
        lottoBundle.add(lotto);
        //when
        Map<Integer, Integer> match = shop.matchAnswer(lottoBundle, answer);
        //then
        assertThat(match.get(3)).isEqualTo(1);
    }
}