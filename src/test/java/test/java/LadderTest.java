package test.java;

import ladder.GreaterThanOne;
import ladder.LadderPosition;
import ladder.creater.LadderCreater;
import ladder.LadderGame;
import ladder.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LadderTest {

    @Test
    @DisplayName("빈 사다리는 항상 제자리로 도착한다")
    void emptyLadder_isIdentity() {
        //given
        LadderCreater ladderCreater = new LadderCreater(GreaterThanOne.from(5),GreaterThanOne.from(4));// 높이, 사람 수
        LadderGame ladderGame = new LadderGame(ladderCreater);
        //when&then
        assertEquals(1, ladderGame.run(LadderPosition.of(0,0)));
        assertEquals(2, ladderGame.run(LadderPosition.of(0,1)));
        assertEquals(3, ladderGame.run(LadderPosition.of(0,2)));
        assertEquals(4, ladderGame.run(LadderPosition.of(0,3)));
    }

    @Test
    @DisplayName("같은 층에서 (col)~(col+1)을 연결하면 양방향으로 서로 바뀐다")
    void singleConnection_swapsNeighbors() {
        //given
        LadderCreater ladderCreater = new LadderCreater(GreaterThanOne.from(2), GreaterThanOne.from(3));
        LadderGame ladderGame = new LadderGame(ladderCreater);

        ladderCreater.drawLine(Position.from(0), Position.from(0)); // row0: 1-2 연결

        //when&then
        assertEquals(2, ladderGame.run(LadderPosition.of(0,0))); // 1→2
        assertEquals(1, ladderGame.run(LadderPosition.of(0,1))); // 2→1
        assertEquals(3, ladderGame.run(LadderPosition.of(0,2))); // 3은 영향 없음
    }

    @Test
    @DisplayName("같은 row에서 연속 가로선은 금지된다")
    void noSequenceDrawLineInSameRow(){
        //given
        LadderCreater ladderCreater = new LadderCreater(GreaterThanOne.from(2), GreaterThanOne.from(5));

        //when
        ladderCreater.drawLine(Position.from(0), Position.from(1));
        assertThrows(IllegalArgumentException.class, () -> ladderCreater.drawLine(Position.from(0), Position.from(0)));
        assertThrows(IllegalArgumentException.class, () -> ladderCreater.drawLine(Position.from(0), Position.from(2)));

        //then
        ladderCreater.drawLine(Position.from(0), Position.from(3));
    }

    @Test
    @DisplayName("왼쪽 연결을 우선 감지한다(오른쪽 검사 전에 왼쪽을 본다)")
    void preferLeftWhenOnRightSideOfABar() {
        //given
        LadderCreater ladderCreater = new LadderCreater(GreaterThanOne.from(2), GreaterThanOne.from(3));
        LadderGame ladderGame = new LadderGame(ladderCreater);
        ladderCreater.drawLine(Position.from(0), Position.from(0)); // row0: 1-2


        //when
        int result = ladderGame.run(LadderPosition.of(0,1)); // col=1에서 왼쪽(rows[0][0]) 먼저 체크

        //then
        assertEquals(1, result);
    }

    @Test
    @DisplayName("오른쪽에 선이 있으면 오른쪽으로, 왼쪽에 선이 있으면 왼쪽으로 이동한다")
    void moveLeftOrRightPerRow() {
        //given
        LadderCreater ladderCreater = new LadderCreater(GreaterThanOne.from(2), GreaterThanOne.from(3));
        LadderGame ladderGame = new LadderGame(ladderCreater);
        ladderCreater.drawLine(Position.from(0), Position.from(0)); // row0: 1-2
        ladderCreater.drawLine(Position.from(1), Position.from(1)); // row1: 2-3

        //when
        int from1 = ladderGame.run(LadderPosition.of(0,0)); // row0: 1→2, row1: 2→3  => 3
        int from2 = ladderGame.run(LadderPosition.of(0,1)); // row0: 2→1(left 우선), row1: 1 유지 => 1
        int from3 = ladderGame.run(LadderPosition.of(0,2)); // row0: 3 유지, row1: 3→2(left) => 2

        //then
        assertEquals(3, from1);
        assertEquals(1, from2);
        assertEquals(2, from3);
    }





}