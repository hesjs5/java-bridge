package bridge.constant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class BridgeMoveTest {

    @ParameterizedTest
    @ValueSource(strings = {"d", "u", "", " ", "a", "da", "ua"})
    void findByInputByNotExist(String input) {
        BridgeMove bridgeMove = BridgeMove.findByInput(input);

        assertThat(bridgeMove).isEqualTo(BridgeMove.MISS);
    }

    static Stream<Arguments> generateFindByInputData() {
        return Stream.of(
                Arguments.of("D", BridgeMove.DOWN),
                Arguments.of("U", BridgeMove.UP)
        );
    }

    @ParameterizedTest
    @MethodSource("generateFindByInputData")
    void findByInputByDOWNExist(String input, BridgeMove bridgeMove) {
        BridgeMove byInput = BridgeMove.findByInput(input);

        assertThat(byInput).isEqualTo(bridgeMove);
    }

    static Stream<Arguments> generateEqualFirstLetterWithInputData() {
        return Stream.of(
                Arguments.of("D", BridgeMove.DOWN, true),
                Arguments.of("U", BridgeMove.UP, true),
                Arguments.of("D", BridgeMove.UP, false),
                Arguments.of("U", BridgeMove.DOWN, false)
        );
    }

    @ParameterizedTest
    @MethodSource("generateEqualFirstLetterWithInputData")
    void equalFirstLetterWithInput_입력값과_BridgeMove의_첫글자가_같은지_확인하는_메서드(String input, BridgeMove bridgeMove, boolean result) {
        boolean equalFirstLetterWithInput = bridgeMove.equalFirstLetterWithInput(input);

        assertThat(equalFirstLetterWithInput).isEqualTo(result);
    }

    static Stream<Arguments> generateIsMissData() {
        return Stream.of(
                Arguments.of(BridgeMove.MISS, true),
                Arguments.of(BridgeMove.DOWN, false),
                Arguments.of(BridgeMove.UP, false)
        );
    }

    @ParameterizedTest
    @MethodSource("generateIsMissData")
    void isMiss_BridgeMove가_MISS인지_확인하는_메서드(BridgeMove bridgeMove, boolean result) {
        boolean miss = bridgeMove.isMiss();

        assertThat(miss).isEqualTo(result);
    }
}