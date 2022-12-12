package bridge;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BridgeMoveResultTest {

    @ParameterizedTest
    @CsvSource({
            "D, true",
            "U, false",
    })
    void isDownMoving(String moving, boolean result) {
        BridgeMoveResult bridgeMoveResult = new BridgeMoveResult(moving, "X");

        assertThat(bridgeMoveResult.isDownMoving()).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource({
            "U, true",
            "D, false",
    })
    void isUpMoving(String moving, boolean result) {
        BridgeMoveResult bridgeMoveResult = new BridgeMoveResult(moving, "X");

        assertThat(bridgeMoveResult.isUpMoving()).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource({
            "X, true",
            "O, false",
    })
    void canNotMove(String moveResult, boolean result) {
        BridgeMoveResult bridgeMoveResult = new BridgeMoveResult("D", moveResult);

        assertThat(bridgeMoveResult.canNotMove()).isEqualTo(result);
    }
}