package lotto.shop;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.Quantity;
import lotto.error.ErrorMessage;

public class MoneyTest {
    @Test
    @DisplayName("돈 생성")
    void create() {
        //given
        Money money = Money.of(14000);
        //when
        int amount = money.amount();
        //then
        assertThat(amount).isEqualTo(14000);
    }

    @Test
    @DisplayName("돈이 음수가 되는 경우")
    void checkNegative() {
        //given
        //when
        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Money.of(-10)).withMessageContaining(ErrorMessage.MONEY_CANNOT_BE_NEGATIVE);
    }

    @Test
    @DisplayName("돈 금액 작은지 비교")
    void lt() {
        //given
        Money money = Money.of(1000);
        //when
        //then
        assertThat(money.lt(Money.of(1100))).isTrue();
    }

    @DisplayName("빼기")
    @Test
    void sub() {
        // given
        Money money = Money.of(1000);
        // when
        Money actual = money.sub(Money.of(700));
        // then
        assertThat(actual).isEqualTo(Money.of(300));
    }

    @DisplayName("곱하기")
    @Test
    void multiplyQuantity() {
        // given
        Money money = Money.of(1000);
        // when
        Money actual = money.multiplyQuantity(new Quantity(3));
        // then
        assertThat(actual).isEqualTo(Money.of(3000));
    }

    @DisplayName("개수 구하기")
    @Test
    void quantity() {
        // given
        Money money = Money.of(1000);
        // when
        int quantity = money.getQuantity(Money.of(1000));
        // then
        assertThat(quantity).isEqualTo(1);
    }
}
