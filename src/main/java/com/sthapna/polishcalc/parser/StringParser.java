package com.sthapna.polishcalc.parser;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StringParser {
    private static final String DELIMITER = " ";

    public static Optional<List<String>> parse(String expr) {
        if(isExprNullOrEmpty(expr))
            return Optional.empty();
        else
            return Optional.of(createTokenList(expr));
    }

    private static List<String> createTokenList(String expr) {
        return Arrays.asList(expr.trim().split(DELIMITER)).stream()
                .filter(e -> !e.trim().isEmpty())
                .collect(Collectors.toList());
    }

    private static boolean isExprNullOrEmpty(String expr) {
        return expr == null || "".equals(expr.trim());
    }
}
