package bridge;

import static bridge.constant.MoveResult.CAN_MOVE;
import static bridge.constant.MoveResult.CAN_NOT_MOVE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import bridge.constant.GameCommand;
import bridge.constant.GameStatus;
import bridge.constant.MoveResult;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class BridgeGameTest {

    private BridgeGame bridgeGame;

    @BeforeEach
    void init() {
        bridgeGame = new BridgeGame();
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(List.of("U", "D", "D"), "U", 0),
                Arguments.of(List.of("U", "D", "D"), "D", 1)
        );
    }

    @ParameterizedTest
    @MethodSource("generateData")
    void move(List<String> bridge, String playerInput,int countOfRound) {
        MoveResult result = bridgeGame.move(bridge, playerInput, countOfRound);

        assertThat(result).isEqualTo(CAN_MOVE);
    }

    static Stream<Arguments> generateCanNotMoveData() {
        return Stream.of(
                Arguments.of(List.of("U", "D", "D"), "U", 1),
                Arguments.of(List.of("U", "D", "D"), "U", 2),
                Arguments.of(List.of("U", "D", "D"), "D", 0)
        );
    }

    @ParameterizedTest
    @MethodSource("generateCanNotMoveData")
    void moveByNotMove(List<String> bridge, String playerInput,int countOfRound) {
        MoveResult result = bridgeGame.move(bridge, playerInput, countOfRound);

        assertThat(result).isEqualTo(CAN_NOT_MOVE);
    }

    @ParameterizedTest
    @ValueSource(ints = {0})
    void retry(int round) {
        int countOfGame = bridgeGame.getBridgeGameStat().getCountOfGame();

        bridgeGame.retry();

        assertThat(bridgeGame.getCountOfRound()).isEqualTo(round);
        assertThat(bridgeGame.getBridgeGameStat().getBridgeMaps()).isEmpty();
        assertThat(bridgeGame.getBridgeGameStat().getGameStatus()).isEqualTo(GameStatus.FAIL);
        assertThat(bridgeGame.getBridgeGameStat().getCountOfGame()).isEqualTo(countOfGame + 1);
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MAX_VALUE})
    void quit(int round) {
        bridgeGame.quit();

        assertThat(bridgeGame.getCountOfRound()).isEqualTo(round);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5})
    void isCountOfRoundLessThan(int bridgeSize) {
        boolean result = bridgeGame.isCountOfRoundLessThan(bridgeSize);

        assertTrue(result);
    }

    @Test
    void nextRound() {
        int countOfRound = bridgeGame.getCountOfRound();

        bridgeGame.nextRound();

        assertThat(bridgeGame.getCountOfRound()).isEqualTo(countOfRound + 1);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5})
    void checkGameWinByNotWin(int bridgeSize) {
        bridgeGame.checkGameWin(bridgeSize);

        assertThat(bridgeGame.getBridgeGameStat().getGameStatus()).isEqualTo(GameStatus.FAIL);
    }

    @ParameterizedTest
    @ValueSource(ints = {3})
    void checkGameWin(int bridgeSize) {
        bridgeGame.nextRound();
        bridgeGame.nextRound();
        bridgeGame.checkGameWin(bridgeSize);

        assertThat(bridgeGame.getBridgeGameStat().getGameStatus()).isEqualTo(GameStatus.SUCCESS);
    }
}