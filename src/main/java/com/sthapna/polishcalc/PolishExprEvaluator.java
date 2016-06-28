package com.sthapna.polishcalc;

import com.sthapna.polishcalc.expression.PostfixExpr;

public class PolishExprEvaluator {
    private static PolishExprEvaluator evaluator;

    public static PolishExprEvaluator make() {
        return evaluator != null ? evaluator : new PolishExprEvaluator();
    }

    public int evaluate(PostfixExpr expr) {

        return 5;
    }
}
