package com.sthapna.polishcalc.expression;

import com.sthapna.polishcalc.parser.StringParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.*;

public class PostfixExpr {

    public final List<String> tokens;

    private PostfixExpr(List<String> tokens) {
        this.tokens = tokens;
    }

    public static PostfixExpr build (String expression) {
        Optional<List<String>> ts = StringParser.parse(expression);
        ExprValidator.validations.forEach(e -> e.validator(ts));
        return  new PostfixExpr(ts.get());
    }

    interface ExprValidator {
        void validator(Optional<List<String>> tokens);

        List<ExprValidator> validations = new ArrayList<ExprValidator>(){{
            add(t -> {if(!t.isPresent()) throw new InvalidExpression("Empty Expression...");});
            add(t -> {if(t.get().size() < 2) throw new InvalidExpression("Single element in Expression...");});
            add(t -> {if(!isPostfixSequenceValid(t)) throw new InvalidExpression("Invalid postfix sequence...");});
        }};

        static boolean isPostfixSequenceValid(Optional<List<String>> tokens){
            return loop(tokens.get(),0);
        }

        static boolean loop(List<String> tokens, int counter) {
            return (tokens.size() == 0)? counter == 0 :
                    isOperand(tokens.get(0)) ? loop(tokens.subList(1,tokens.size()), abs(counter) + 1):
                            isBinaryOperator(tokens.get(0)) ? loop(tokens.subList(1,tokens.size()),abs(counter) - 2):
                                    isUnaryOperator(tokens.get(0)) ?loop(tokens.subList(1,tokens.size()),abs(counter) - 1):
                                            loop(tokens.subList(1,tokens.size()),abs(counter));
        }

        static boolean isUnaryOperator(String element) {
            return false;
        }

        static boolean isOperand(String element) {
            return element.matches("\\d");
        }

        static boolean isBinaryOperator(String element) {
            return element.matches("[+|-|*|/]");
        }
    }

    public static class InvalidExpression extends RuntimeException{

        public InvalidExpression(String message) {
            super(message);
        }
    }
}
