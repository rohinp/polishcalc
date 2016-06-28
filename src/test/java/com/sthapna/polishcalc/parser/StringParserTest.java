package com.sthapna.polishcalc.parser;

import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class StringParserTest {
    @Test
    public void itShouldNotParseEmptyPostFixExpression(){
        //given
        Optional<List<String>> tokens =  StringParser.parse("");
        //when

        //then
        assertEquals(Optional.empty(),tokens);
    }

    @Test
    public void itShouldParsePostFixExpressionSeparatedBySpaceIntoTokens(){
        //given
        Optional<List<String>> tokens =  StringParser.parse("12 13 +");
        //when

        //then
        assertArrayEquals(new String[]{"12","13","+"},tokens.get().toArray());
    }

    @Test
    public void itShouldTrimAndParsePostFixExpressionSeparatedBySpaceIntoTokens(){
        //given
        Optional<List<String>> tokens =  StringParser.parse("     12    13   +     ");
        //when

        //then
        assertArrayEquals(new String[]{"12","13","+"},tokens.get().toArray());
    }

}
