package bridge;

import static org.assertj.core.api.Assertions.assertThat;

import bridge.constant.GameStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BridgeGameStatTest {

    private BridgeGameStat bridgeGameStat;
    private int countOfGame;

    @BeforeEach
    void init() {
        this.bridgeGameStat = new BridgeGameStat();
        this.countOfGame = bridgeGameStat.getCountOfGame();
    }

    @Test
    void restart() {
        bridgeGameStat.restart();

        assertThat(bridgeGameStat.getBridgeMaps()).isEmpty();
        assertThat(bridgeGameStat.getGameStatus()).isEqualTo(GameStatus.FAIL);
        assertThat(bridgeGameStat.getCountOfGame()).isEqualTo(countOfGame + 1);
    }

    @Test
    void gameWin() {
        bridgeGameStat.gameWin();

        assertThat(bridgeGameStat.getGameStatus()).isEqualTo(GameStatus.SUCCESS);
    }
}