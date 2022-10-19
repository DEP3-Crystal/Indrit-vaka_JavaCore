package com.crystal.hangman.string_joing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestStringJoin {
    @Test
    void testStringJoin() {
        String[] data = {"indrit", "vaka"};
        String actual = String.join(",", data);
        Assertions.assertEquals("indrit,vaka", actual);
    }
}
