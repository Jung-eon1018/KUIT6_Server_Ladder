package test.java;

import ladder.*;
import ladder.creater.LadderCreater;
import ladder.GreaterThanOne;
import ladder.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LadderViewerTest {

    @Test
    @DisplayName("사용자가 사람 수를 입력하면 빈 사다리를 출력한다.")
    void printEmptyLadder() {
        //given
        LadderCreater creator = new LadderCreater(GreaterThanOne.from(4), GreaterThanOne.from(5));
        LadderViewer viewer = new LadderViewer();
        //when
        viewer.snapshotAllRows(creator.getRows(), null);
        String actual = viewer.render();
        //then
        System.out.println("빈 사다리 생성\n");
        System.out.print(viewer.render());
        StringBuilder sb = new StringBuilder();
        sb.append("1 1 1 1 1\n")
                .append("1 1 1 1 1\n")
                .append("1 1 1 1 1\n")
                .append("1 1 1 1 1\n\n");
        String expected = sb.toString();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("사용자가 좌표를 입력하면 사다리를 생성한다.")
    void drawLadderLine (){
        //given
        LadderCreater creator = new LadderCreater(GreaterThanOne.from(3), GreaterThanOne.from(5));
        creator.drawLine(Position.from(0), Position.from(1));
        creator.drawLine(Position.from(2), Position.from(3));
        LadderViewer viewer = new LadderViewer();
        //when
        viewer.snapshotAllRows(creator.getRows(), null);
        String actual = viewer.render();
        //then
        System.out.println("사용자가 사다리 라인 생성\n");
        System.out.print(viewer.render());
        StringBuilder sb = new StringBuilder();
        sb.append("1 1 -1 1 1\n")
                .append("1 1 1 1 1\n")
                .append("1 1 1 1 -1\n\n");
        String expected = sb.toString();
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    @DisplayName("사용자가 시작 위치 사다리(열)을 선택하면 사다리를 타고 내려간다.")
    void userMove() {
        //given
        LadderCreater creator = new LadderCreater(GreaterThanOne.from(2), GreaterThanOne.from(3));
        creator.drawLine(Position.from(0), Position.from(0));
        creator.drawLine(Position.from(1), Position.from(1));
        LadderViewer viewer = new LadderViewer();
        LadderRunner runner = new LadderRunner(creator.getRows(), viewer);
        //when
        runner.run(LadderPosition.of(0,1));
        String actual = viewer.render();
        //then
        System.out.println("사다리 시작 위치 선택 시 위치 변화\n");
        System.out.print(viewer.render());
        StringBuilder sb = new StringBuilder();
        sb.append("Before\n")
                .append("1 -1* 1\n")
                .append("1 1 -1\n\n")
                .append("After\n")
                .append("1* -1 1\n")
                .append("1 1 -1\n\n\n")
                .append("Before\n")
                .append("1 -1 1\n")
                .append("1* 1 -1\n\n")
                .append("After\n")
                .append("1 -1 1\n")
                .append("1* 1 -1\n\n\n");

        String expected = sb.toString();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("콘솔로 눈검: 출력만 확인")
    void just_print_rendered_view() {
        LadderCreater creator = new LadderCreater(GreaterThanOne.from(3), GreaterThanOne.from(5));
        creator.drawLine(Position.from(0), Position.from(0)); // row0: (2~3)
        creator.drawLine(Position.from(2), Position.from(3)); // row2: (4~5)

        LadderViewer viewer = new LadderViewer();
        LadderRunner runner = new LadderRunner(creator.getRows(), viewer);

      ; // 1번 참가자(0-based)
        runner.run(LadderPosition.of(0,0));

        System.out.println("\n--- Rendered Ladder ---");
        System.out.print(viewer.render());
        // 출력만 하고 별도의 assert는 없음
    }

}
