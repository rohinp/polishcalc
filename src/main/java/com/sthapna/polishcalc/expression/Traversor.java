package com.sthapna.polishcalc.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.sthapna.polishcalc.expression.Traversor.Pair.DEFAULT;
import static com.sthapna.polishcalc.expression.Traversor.Pair.pair;

enum ElmType {
    OPERAND, BIOPR,UOPR,UNKOWN
}

@FunctionalInterface
interface FuncAdder {
    Stack<Integer> apply(Stack<Integer> acc, ElmType elmType, String token);
}

@FunctionalInterface
interface Selector {
    boolean apply(String elem);
}

class Traversor {

    static class Pair{
        final Selector _1;
        final ElmType _2;
        static final Pair DEFAULT = pair( (x -> true) , ElmType.UNKOWN);

        private Pair(Selector __1, ElmType __2) {
            _1 = __1;
            _2 = __2;
        }

        static Pair pair(Selector __1, ElmType __2){
            return new Pair(__1,__2);
        }
    }

    static List<Pair> selectors = new ArrayList<Pair>(){{
        add(pair(Traversor::isOperand, ElmType.OPERAND));
        add(pair(Traversor::isBinaryOperator, ElmType.BIOPR));
        add(pair(Traversor::isUnaryOperator, ElmType.UOPR));
    }};

    static Stack<Integer> start(List<String> tokens, FuncAdder fa) {
        return loop(tokens,new Stack<>(),fa);
    }

    static Stack<Integer> loop(List<String> tokens, Stack<Integer> acc, FuncAdder fa) {
        if(tokens.size() == 0) return acc;
        else
            return loop(tokens.subList(1,tokens.size()),applyOpr(tokens.get(0),acc,fa), fa);
    }

    private static Stack<Integer> applyOpr(String token, Stack<Integer> acc, FuncAdder fa) {
        ElmType et = selectors.stream().filter(e -> e._1.apply(token)).findFirst().orElse(DEFAULT)._2;
        return fa.apply(acc, et,token);
    }

    static boolean isUnaryOperator(String element) {
        return element.matches("^");
    }

    static boolean isOperand(String element) {
        return element.matches("\\d+");
    }

    static boolean isBinaryOperator(String element) {
        return element.matches("[\\+|\\-|\\*|/]");
    }
}
