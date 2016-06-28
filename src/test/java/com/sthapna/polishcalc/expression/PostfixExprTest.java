package com.sthapna.polishcalc.expression;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PostfixExprTest {

    @Test(expected = PostfixExpr.InvalidExpression.class)
    public void itShouldValidateAnEmptyPostFixExpression(){
        //given
        PostfixExpr.build("");
        //when

        //then
    }

    @Test(expected = PostfixExpr.InvalidExpression.class)
    public void itShouldInValidateASingleElementPostFixExpression(){
        //given
        PostfixExpr.build("1");
        //when

        //then
    }

    @Test(expected = PostfixExpr.InvalidExpression.class)
    public void itShouldInValidateOperatorOperandSequence_1(){
        //given
        PostfixExpr.build("1 2");
        //when

        //then
    }

    @Test(expected = PostfixExpr.InvalidExpression.class)
    public void itShouldInValidateOperatorOperandSequence_2(){
        //given
        PostfixExpr.build("1 + 2");
        //when

        //then
    }

    @Test(expected = PostfixExpr.InvalidExpression.class)
    public void itShouldInValidateAlphanumericElementsInPostFixExpression(){
        //given
        PostfixExpr.build("x y 3");
        //when

        //then
    }

    @Test
    public void itShouldValidateAValidPostFixExpression_TwoOperandOneOperator(){
        //given
        PostfixExpr postfixExpr = PostfixExpr.build("2 3 +");
        //when

        //then
        assertTrue(postfixExpr.tokens.size() == 3);
    }


}
