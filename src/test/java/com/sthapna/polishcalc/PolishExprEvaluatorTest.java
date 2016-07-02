package com.sthapna.polishcalc;

import com.sthapna.polishcalc.expression.PolishExprEvaluator;
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

    @Test
    public void itShouldEvaluatePolishExpression_Subtraction(){
        //given
        int expected = 5;
        //when
        int actual = PolishExprEvaluator.make().evaluate(PostfixExpr.build("20 15 -"));
        //then
        assertEquals(expected,actual);
    }

    @Test
    public void itShouldEvaluatePolishExpression_Multiplication(){
        //given
        int expected = 30;
        //when
        int actual = PolishExprEvaluator.make().evaluate(PostfixExpr.build("2 15 *"));
        //then
        assertEquals(expected,actual);
    }

    @Test
    public void itShouldEvaluatePolishExpression_Division(){
        //given
        int expected = 2;
        //when
        int actual = PolishExprEvaluator.make().evaluate(PostfixExpr.build("10 5 /"));
        //then
        assertEquals(expected,actual);
    }
}
