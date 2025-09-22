package main.java;

public class LadderGame {
    private final LadderCreater ladderCreater;

    public LadderGame(LadderCreater ladderCreater) {
        this.ladderCreater = ladderCreater;
    }

    public int run(Position position){
        //1. 사다리의 생성을 요청
        Row[] rows = ladderCreater.getRows();

        //2. runner에게 완성된 사다리를 건네준다.
        LadderRunner ladderRunner = new LadderRunner(rows);

        //3. runner에게 사다리를 타라고 메세지를 보낸다.
        return ladderRunner.run(position);
    }
}
