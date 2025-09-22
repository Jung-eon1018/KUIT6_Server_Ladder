package test.java;

import main.java.GreaterThanOne;
import main.java.LadderCreater;
import main.java.LadderGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class LadderCreaterTest {

    @Test
    @DisplayName("사다리 생성 확인")
    void testCreateLadder() {
        //given
        GreaterThanOne numberofRow = GreaterThanOne.from(3);
        GreaterThanOne numberofPerson = GreaterThanOne.from(3);

        //when
        LadderCreater ladderCreater = new LadderCreater(numberofRow, numberofPerson);

        //then
        assertThat(ladderCreater).isNotNull();

    }

}
