package com.sthapna.polishcalc.expression;

import java.util.Stack;

import static java.lang.Integer.parseInt;

public class PolishExprEvaluator {
    private static PolishExprEvaluator evaluator;

    public static PolishExprEvaluator make() {
        return evaluator != null ? evaluator : (evaluator = new PolishExprEvaluator());
    }

    public int evaluate(PostfixExpr expr) {
        return Traversor.start(expr.tokens,PolishExprEvaluator::calculate).pop();
    }

    static Stack<Integer> calculate(Stack<Integer> acc, ElmType et, String token){
        if(et.equals(ElmType.OPERAND)) acc.push(parseInt(token));
        if(et.equals(ElmType.UNKOWN)) acc.push(1);
        if(et.equals(ElmType.BIOPR)) {
            if("+".equals(token))
                acc.push(acc.pop() + acc.pop());
            if("-".equals(token))
                acc.push(-acc.pop() + acc.pop());
            if("*".equals(token))
                acc.push(acc.pop() * acc.pop());
            if("/".equals(token)) {
                int div = acc.pop();
                acc.push(acc.pop() / div);
            }
        }
        return acc;
    }
}
