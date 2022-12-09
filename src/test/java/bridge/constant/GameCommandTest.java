package bridge.constant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class GameCommandTest {

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of("R", GameCommand.RESTART),
                Arguments.of("Q", GameCommand.QUIT)
        );
    }

    @ParameterizedTest
    @MethodSource("generateData")
    void findByString(String string, GameCommand result) {
        GameCommand gameCommand = GameCommand.findByString(string);

        assertThat(gameCommand).isNotNull();
        assertThat(gameCommand).isNotSameAs(GameCommand.MISS);
        assertThat(gameCommand).isEqualTo(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"r", "q", "", " ", "ar", "aq"})
    void findByStringThatReturnMISS(String string) {
        GameCommand gameCommand = GameCommand.findByString(string);

        assertThat(gameCommand).isNotNull();
        assertThat(gameCommand).isEqualTo(GameCommand.MISS);
    }

    @ParameterizedTest
    @MethodSource("generateData")
    void equalFirstLetterWithString_입력값과_GameCommand가_일치하면_참을_반환(String string, GameCommand gameCommand) {
        boolean equalFirstLetterWithString = gameCommand.equalFirstLetterWithString(string);

        assertTrue(equalFirstLetterWithString);
    }

    @ParameterizedTest
    @ValueSource(strings = {"r", "q", "", " ", "ar", "aq"})
    void equalFirstLetterWithString_입력값과_GameCommand가_일치하지_않으면_거짓을_반환(String string) {
        GameCommand gameCommand = GameCommand.QUIT;

        boolean result = gameCommand.equalFirstLetterWithString(string);

        assertFalse(result);
    }

    static Stream<Arguments> generateIsMissData() {
        return Stream.of(
                Arguments.of(GameCommand.MISS, true),
                Arguments.of(GameCommand.RESTART, false),
                Arguments.of(GameCommand.QUIT, false)
        );
    }

    @ParameterizedTest
    @MethodSource("generateIsMissData")
    void isMiss(GameCommand gameCommand, boolean result) {
        boolean miss = gameCommand.isMiss();

        assertThat(miss).isEqualTo(result);
    }

    static Stream<Arguments> generateIsRestartData() {
        return Stream.of(
                Arguments.of(GameCommand.MISS, false),
                Arguments.of(GameCommand.RESTART, true),
                Arguments.of(GameCommand.QUIT, false)
        );
    }

    @ParameterizedTest
    @MethodSource("generateIsRestartData")
    void isRestart(GameCommand gameCommand, boolean result) {
        boolean restart = gameCommand.isRestart();

        assertThat(restart).isEqualTo(result);
    }

    static Stream<Arguments> generateIsQuitData() {
        return Stream.of(
                Arguments.of(GameCommand.MISS, false),
                Arguments.of(GameCommand.RESTART, false),
                Arguments.of(GameCommand.QUIT, true)
        );
    }

    @ParameterizedTest
    @MethodSource("generateIsQuitData")
    void isQuit(GameCommand gameCommand, boolean result) {
        boolean quit = gameCommand.isQuit();

        assertThat(quit).isEqualTo(result);
    }
}