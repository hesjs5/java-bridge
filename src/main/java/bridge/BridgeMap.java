package bridge;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BridgeMap implements Iterable<BridgeMoveResult> {

    List<BridgeMoveResult> map;

    public BridgeMap() {
        init();
    }

    public void add(BridgeMoveResult bridgeMoveResult) {
        this.map.add(bridgeMoveResult);
    }

    public boolean isAppendableDividingLine(int index) {
        return (1 < map.size()) && (index < map.size() - 1);
    }

    public void init() {
        this.map = new ArrayList<>();
    }

    @Override
    public Iterator<BridgeMoveResult> iterator() {
        return map.iterator();
    }
}
