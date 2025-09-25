package ladder;

import ladder.LadderRunner;
import ladder.creater.LadderCreater;

public class LadderGame {
    private final LadderCreater ladderCreater;

    public LadderGame(LadderCreater ladderCreater) {
        this.ladderCreater = ladderCreater;
    }

    public int run(LadderPosition startPos){
        Row[] rows = ladderCreater.getRows();
        LadderRunner ladderRunner = new LadderRunner(rows);

        int resultCol = ladderRunner.run(startPos);

        return resultCol + 1;
    }
}
