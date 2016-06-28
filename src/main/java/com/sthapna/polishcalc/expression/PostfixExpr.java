package com.sthapna.polishcalc.expression;

import com.sthapna.polishcalc.parser.StringParser;

import java.util.ArrayList;
import java.util.Collections;
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
            return Traversor.loop(tokens.get(),0,ExprValidator::checkSequence) == 0;
        }

        static int checkSequence(int acc, ElementType elementType) {
            return elementType == ElementType.OPR ? abs(acc) + 1:
                    elementType == ElementType.BOPR ? abs(acc) - 2:
                            elementType == ElementType.UOPR ? abs(acc) - 1:
                                    abs(acc) - 1;
        }

    }

    public static class InvalidExpression extends RuntimeException{

        public InvalidExpression(String message) {
            super(message);
        }
    }
}
