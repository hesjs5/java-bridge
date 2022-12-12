package bridge;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BridgeMakerTest {

    private BridgeMaker bridgeMaker;

    @BeforeEach
    void init() {
        this.bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7, 8, 9, 10})
    void makeBridge(int bridgeSize) {
        List<String> bridge = bridgeMaker.makeBridge(bridgeSize);

        assertThat(bridge.size()).isEqualTo(bridgeSize);
        assertThat(bridge).containsAnyOf("U", "D");
    }
}