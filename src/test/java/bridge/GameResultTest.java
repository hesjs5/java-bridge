package bridge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class GameResultTest {

    private GameResult gameResult;

    @BeforeEach
    void init() {
        gameResult = new GameResult();
    }

    @Test
    void isGameOver_false() {
        assertFalse(gameResult.isGameOver());
    }

    @Test
    void isGameOver_true() {
        gameResult.gameOver();

        assertTrue(gameResult.isGameOver());
    }

    @Test
    void gameOver() {
        boolean isGameOver = gameResult.isGameOver();

        gameResult.gameOver();

        assertFalse(isGameOver);
        assertTrue(gameResult.isGameOver());
    }

    @Test
    void toGameStatus() {
        assertThat(gameResult.toGameStatus()).isEqualTo("성공");

        gameResult.gameOver();

        assertThat(gameResult.toGameStatus()).isEqualTo("실패");
    }

    @Test
    void addCountOfTry() {
        int oldValue = gameResult.getCountOfTry();

        gameResult.addCountOfTry();

        int newValue = gameResult.getCountOfTry();
        assertThat(newValue).isEqualTo(oldValue + 1);
    }

    @Test
    void update() {
        BridgeMap bridgeMap = new BridgeMap();
        boolean gameStatus = false;

        gameResult.update(bridgeMap, gameStatus);

        assertThat(gameResult.getMap()).isEqualTo(bridgeMap);
        assertThat(gameResult.getGameStatus()).isEqualTo(gameStatus);
    }
}