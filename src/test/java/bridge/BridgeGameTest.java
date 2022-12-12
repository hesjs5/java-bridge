package bridge;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BridgeGameTest {

    private BridgeGame bridgeGame;

    @BeforeEach
    void init() {
        this.bridgeGame = new BridgeGame();
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of("D", 0, "X"),
                Arguments.of("U", 1, "X"),
                Arguments.of("U", 1, "X"),
                Arguments.of("U", 0, "O"),
                Arguments.of("D", 1, "O"),
                Arguments.of("D", 2, "O")
        );
    }

    @ParameterizedTest
    @MethodSource("generateData")
    void move(String moving, int round, String moveResult) {
        Bridge bridge = new Bridge(List.of("U", "D", "D"));

        String isMove = bridgeGame.move(bridge, moving, round);

        assertThat(isMove).isEqualTo(moveResult);
    }
}