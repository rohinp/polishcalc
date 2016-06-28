package com.sthapna.polishcalc.expression;

import java.util.List;

enum ElementType {
    OPR,BOPR,UOPR,UNKOWN;
}

@FunctionalInterface
interface FunctionAdder {
    int apply(int acc,ElementType elementType);

}

class Traversor {

    static int loop(List<String> tokens, int acc, FunctionAdder fa) {
        return (tokens.size() == 0)? acc:
                isOperand(tokens.get(0)) ? loop(tokens.subList(1,tokens.size()),fa.apply(acc, ElementType.OPR) ,fa):
                        isBinaryOperator(tokens.get(0)) ? loop(tokens.subList(1,tokens.size()),fa.apply(acc, ElementType.BOPR) ,fa):
                                isUnaryOperator(tokens.get(0)) ?loop(tokens.subList(1,tokens.size()),fa.apply(acc, ElementType.UOPR) ,fa):
                                        loop(tokens.subList(1,tokens.size()),fa.apply(acc, ElementType.UNKOWN) ,fa);
    }

    static boolean isUnaryOperator(String element) {
        return element.matches("^");
    }

    static boolean isOperand(String element) {
        return element.matches("\\d");
    }

    static boolean isBinaryOperator(String element) {
        return element.matches("[+|-|*|/]");
    }
}
