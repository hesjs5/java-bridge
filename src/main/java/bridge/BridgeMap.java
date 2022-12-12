package bridge;

import java.util.ArrayList;
import java.util.List;

public class BridgeMap {

    List<BridgeMoveResult> map;

    public BridgeMap() {
        this.map = new ArrayList<>();
    }

    public void add(BridgeMoveResult bridgeMoveResult) {
        this.map.add(bridgeMoveResult);
    }

    public List<BridgeMoveResult> getMap() {
        return map;
    }
}
