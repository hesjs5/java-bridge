package bridge;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class GameCommandTest {

    private static final String ERROR_MESSAGE = "[ERROR]";

    @ParameterizedTest
    @ValueSource(strings = {"a", "r", "q", "1", "0", " ", "", "R1", "Qa"})
    void GameCommand_R_또는_Q값이_아닌_경우_예외_발생(String gameCommand) {
        assertThatThrownBy(() -> new GameCommand(gameCommand))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"R", "Q"})
    void GameCommand_R_또는_Q값인_경우_예외_발생하지_않음(String gameCommand) {
        assertDoesNotThrow(() -> new GameCommand(gameCommand));
    }

    @Test
    void isRestart_GameCommand가_RESTART인_경우_참을_반환() {
        GameCommand gameCommand = new GameCommand("R");

        assertTrue(gameCommand.isRestart());
        assertFalse(gameCommand.isQuit());
    }

    @Test
    void isQuit_GameCommand가_QUIT인_경우_참을_반환() {
        GameCommand gameCommand = new GameCommand("Q");

        assertTrue(gameCommand.isQuit());
        assertFalse(gameCommand.isRestart());
    }
}