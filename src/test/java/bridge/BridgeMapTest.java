package bridge;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BridgeMapTest {

    private BridgeMap bridgeMap;

    @BeforeEach
    void init() {
        this.bridgeMap = new BridgeMap();
    }

    @Test
    void add() {
        BridgeMoveResult bridgeMoveResult = new BridgeMoveResult("U", "X");

        bridgeMap.add(bridgeMoveResult);

        for (BridgeMoveResult bridgeMoveResult1 : bridgeMap) {
            assertThat(bridgeMoveResult1).isEqualTo(bridgeMoveResult);
        }
    }

    @ParameterizedTest
    @CsvSource({
            "1, 0, false",
            "5, 4, false",
            "3, 0, true",
            "3, 1, true",
            "4, 2, true"
    })
    void isAppendableDividingLine(int bridgeSize, int index, boolean result) {
        for (int i = 0; i < bridgeSize; i++) {
            bridgeMap.add(new BridgeMoveResult("U", "O"));
        }

        boolean appendableDividingLine = bridgeMap.isAppendableDividingLine(index);

        assertThat(appendableDividingLine).isEqualTo(result);
    }

    @Test
    void initTest() {
        bridgeMap.init();

        assertThat(bridgeMap).isEmpty();
    }
}