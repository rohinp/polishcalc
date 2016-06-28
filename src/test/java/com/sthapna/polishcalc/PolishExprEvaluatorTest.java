package com.sthapna.polishcalc;

import com.sthapna.polishcalc.expression.PostfixExpr;
import org.junit.Test;
import static org.junit.Assert.*;

public class PolishExprEvaluatorTest {
    @Test
    public void itShouldEvaluatePolishExpression_Addition(){
        //given
        int expected = 5;
        //when
        int actual = PolishExprEvaluator.make().evaluate(PostfixExpr.build("2 3 +"));
        //then
        assertEquals(expected,actual);

    }
}
