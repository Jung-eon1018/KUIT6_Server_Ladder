package test.java;

import main.java.Ladder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LadderTest {

    @Test
    @DisplayName("빈 사다리는 항상 제자리로 도착한다")
    void emptyLadder_isIdentity() {
        //given
        Ladder ladder = new Ladder(5, 4); // 높이, 사람 수
        //when&then
        assertEquals(1, ladder.run(1));
        assertEquals(2, ladder.run(2));
        assertEquals(3, ladder.run(3));
        assertEquals(4, ladder.run(4));
    }

    @Test
    @DisplayName("같은 층에서 (col)~(col+1)을 연결하면 양방향으로 서로 바뀐다")
    void singleConnection_swapsNeighbors() {
        //given
        Ladder ladder = new Ladder(1, 3);
        ladder.drawLine(0, 0); // row0: 1-2 연결

        //when&then
        assertEquals(2, ladder.run(1)); // 1→2
        assertEquals(1, ladder.run(2)); // 2→1
        assertEquals(3, ladder.run(3)); // 3은 영향 없음
    }

    @Test
    @DisplayName("같은 row에서 연속 가로선은 금지된다")
    void noSequenceDrawLineInSameRow(){
        //given
        Ladder ladder = new Ladder(1, 5);

        //when
        ladder.drawLine(0, 1);
        assertThrows(IllegalArgumentException.class, () -> ladder.drawLine(0, 0));
        assertThrows(IllegalArgumentException.class, () -> ladder.drawLine(0, 2));

        //then
        ladder.drawLine(0, 3);
    }

    @Test
    @DisplayName("왼쪽 연결을 우선 감지한다(오른쪽 검사 전에 왼쪽을 본다)")
    void preferLeftWhenOnRightSideOfABar() {
        //given
        Ladder ladder = new Ladder(1, 3);
        ladder.drawLine(0, 0); // row0: 1-2

        //when
        int result = ladder.run(2); // col=1에서 왼쪽(rows[0][0]) 먼저 체크

        //then
        assertEquals(1, result);
    }

    @Test
    @DisplayName("오른쪽에 선이 있으면 오른쪽으로, 왼쪽에 선이 있으면 왼쪽으로 이동한다")
    void moveLeftOrRightPerRow() {
        //given
        Ladder ladder = new Ladder(2, 3);
        ladder.drawLine(0, 0); // row0: 1-2
        ladder.drawLine(1, 1); // row1: 2-3

        //when
        int from1 = ladder.run(1); // row0: 1→2, row1: 2→3  => 3
        int from2 = ladder.run(2); // row0: 2→1(left 우선), row1: 1 유지 => 1
        int from3 = ladder.run(3); // row0: 3 유지, row1: 3→2(left) => 2

        //then
        assertEquals(3, from1);
        assertEquals(1, from2);
        assertEquals(2, from3);
    }

    @Test
    @DisplayName("연속되지 않는 지그재그에서도 올바른 최종 위치를 반환한다")
    void zigzag_multipleRows() {
        //given
        Ladder ladder = new Ladder(4, 4);
        ladder.drawLine(0, 0);
        ladder.drawLine(1, 1);
        ladder.drawLine(2, 2);
        ladder.drawLine(3, 1);

        //when
        int a = ladder.run(1);
        int b = ladder.run(2);
        int c = ladder.run(3);
        int d = ladder.run(4);

        //then
        assertEquals(4, a);
        assertEquals(2, b);
        assertEquals(2, c);
        assertEquals(4, d);
    }





}