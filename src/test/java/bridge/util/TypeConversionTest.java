package bridge.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TypeConversionTest {

    private static final String ERROR_MESSAGE = "[ERROR]";

    @ParameterizedTest
    @ValueSource(strings = {"abc", "", " ", "3a"})
    void stringToInt_정수가_아닌_값은_예외_발생(String str) {
        assertThatThrownBy(() -> TypeConversion.stringToInt(str))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "0", "1", "3"})
    void stringToInt_정수는_예외_발생하지_않음(String str) {
        assertDoesNotThrow(() -> TypeConversion.stringToInt(str));
    }
}