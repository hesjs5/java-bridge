package bridge;

import static bridge.constant.MoveResult.CAN_MOVE;
import static bridge.constant.MoveResult.CAN_NOT_MOVE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import bridge.constant.GameStatus;
import bridge.constant.MoveResult;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BridgeGameTest {

    private BridgeGame bridgeGame;

    @BeforeEach
    void init() {
        bridgeGame = new BridgeGame();
    }

    @ParameterizedTest
    @ValueSource(ints = {0})
    void moveByU(int count) {
        List<String> bridge = List.of("U", "D", "D");
        String player = "U";

        MoveResult result = bridgeGame.move(bridge, player, count);

        assertThat(result).isEqualTo(CAN_MOVE);
    }

    @ParameterizedTest
    @ValueSource(ints = {1})
    void moveByD(int count) {
        List<String> bridge = List.of("U", "D", "D");
        String player = "D";

        MoveResult result = bridgeGame.move(bridge, player, count);

        assertThat(result).isEqualTo(CAN_MOVE);
    }

    @ParameterizedTest
    @ValueSource(ints = {0})
    void moveByNotMove(int count) {
        List<String> bridge = List.of("U", "D", "D");
        String player = "D";

        MoveResult result = bridgeGame.move(bridge, player, count);

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
    void checkGameWinByNotWIng(int bridgeSize) {
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