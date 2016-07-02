package com.sthapna.polishcalc.expression;

import com.sthapna.polishcalc.parser.StringParser;

import java.util.*;

import static java.lang.Integer.parseInt;

public class PostfixExpr {

    public final List<String> tokens;

    private PostfixExpr(List<String> tokens) {
        this.tokens = tokens;
    }

    public static PostfixExpr build (String expression) {
        Optional<List<String>> ts = StringParser.parse(expression);
        ExprValidator.validations.forEach(e -> e.validator(ts));
        return  new PostfixExpr(Collections.unmodifiableList(ts.get()));
    }

    interface ExprValidator {
        void validator(Optional<List<String>> tokens);

        List<ExprValidator> validations = new ArrayList<ExprValidator>(){{
            add(t -> {if(!t.isPresent()) throw new InvalidExpression("Empty Expression...");});
            add(t -> {if(t.get().size() < 2) throw new InvalidExpression("Single element in Expression...");});
            add(t -> {if(!isPostfixSequenceValid(t)) throw new InvalidExpression("Invalid postfix sequence...");});
        }};

        static boolean isPostfixSequenceValid(Optional<List<String>> tokens){
            return Traversor.start(tokens.get(),ExprValidator::checkSequence).size() == 1;
        }

        static Stack<Integer> checkSequence(Stack<Integer> acc, ElmType et, String token) {
            if(et.equals(ElmType.OPERAND)) acc.push(parseInt(token));
            if(et.equals(ElmType.UNKOWN)) acc.push(1);
            if(et.equals(ElmType.BIOPR) && acc.size() >= 2) {
                acc.pop();acc.pop();
                acc.push(1);
            }
            return acc;
        }
    }

    public static class InvalidExpression extends RuntimeException{

        public InvalidExpression(String message) {
            super(message);
        }
    }
}
