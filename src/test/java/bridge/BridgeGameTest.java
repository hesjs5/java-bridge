package bridge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
                Arguments.of(List.of("D"), "X"),
                Arguments.of(List.of("U"), "O"),
                Arguments.of(List.of("U", "U"), "OX"),
                Arguments.of(List.of("U", "D"), "OO"),
                Arguments.of(List.of("U", "D", "U"), "OOX"),
                Arguments.of(List.of("U", "D", "D"), "OOO")
        );
    }

    @ParameterizedTest
    @MethodSource("generateData")
    void move(List<String> movings, String moveResult) {
        Bridge bridge = new Bridge(List.of("U", "D", "D"));

        StringBuilder result = new StringBuilder();
        for (String moving : movings) {
            result.append(bridgeGame.move(bridge, moving));
        }

        assertThat(result.toString()).isEqualTo(moveResult);
    }

    @ParameterizedTest
    @MethodSource("generateData")
    void retry(List<String> movings) {
        Bridge bridge = new Bridge(List.of("U", "D", "D"));
        for (String moving : movings) {
            bridgeGame.move(bridge, moving);
        }

        bridgeGame.retry(new BridgeMap(), new GameResult());

        assertTrue(bridgeGame.isRoundLessThan(1));
    }
}