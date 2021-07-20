package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.lotto.Lotto;

class QuantityTest {
    @DisplayName("수량 생성")
    @Test
    void create() {
        // given
        Quantity quantity = new Quantity(5);
        // when
        // then
        assertThat(quantity.getAmount()).isEqualTo(5);
    }

    @DisplayName("수량에 맞게 로또 생성")
    @Test
    void createLotto() {
        // given
        Quantity quantity = new Quantity(5);
        // when
        List<Lotto> lottos = quantity.markingLotto();
        // then
        assertThat(lottos.size()).isEqualTo(5);
    }

    @DisplayName("음수 생성 에러")
    @Test
    void createFailedByAmount() {
        // given
        // when
        // then
        assertThatThrownBy(() -> new Quantity(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}