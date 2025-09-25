package test.java;

import ladder.GreaterThanOne;
import ladder.creater.LadderCreater;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class LadderCreaterTest {

    @Test
    @DisplayName("사다리 생성 확인")
    void testCreateLadder() {
        //given
        GreaterThanOne numberOfRow = GreaterThanOne.from(3);
        GreaterThanOne numberOfPerson = GreaterThanOne.from(3);

        //when
        LadderCreater ladderCreater = new LadderCreater(numberOfRow, numberOfPerson);

        //then
        assertThat(ladderCreater).isNotNull();

    }


}
