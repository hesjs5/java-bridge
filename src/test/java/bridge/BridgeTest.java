package bridge;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BridgeTest {

    private static final String ERROR_MESSAGE = "[ERROR]";

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1, 2, 21})
    void Bridge_다리의_길이가_3부터_20사이가_아니면_예외_발생(int bridgeSize) {
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());

        assertThatThrownBy(() -> new Bridge(bridgeMaker.makeBridge(bridgeSize)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20})
    void Bridge_다리의_길이가_3부터_20사이면_예외_발생하지_않음(int bridgeSize) {
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());

        assertDoesNotThrow(() -> new Bridge(bridgeMaker.makeBridge(bridgeSize)));
    }

    @Test
    void Bridge_다리의_값이_U_또는_D가_아니면_예외_발생() {
        List<String> bridge = List.of("U", "D", "A");

        assertThatThrownBy(() -> new Bridge(bridge))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE);
    }

    @Test
    void Bridge_다리의_값이_U_또는_D라면_예외_발생하지_않음() {
        List<String> bridge = List.of("U", "D", "D");

        assertDoesNotThrow(() -> new Bridge(bridge));
    }
}