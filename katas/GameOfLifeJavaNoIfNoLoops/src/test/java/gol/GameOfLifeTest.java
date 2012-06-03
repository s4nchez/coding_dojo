package gol;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameOfLifeTest {

    @Test
    public void oscillatorsTest() {
        assertNextStep(
                new GameOfLife( ".....",
                                "..*..",
                                "..*..",
                                "..*..",
                                "....."),
                new GameOfLife( ".....",
                                ".....",
                                ".***.",
                                ".....",
                                "....."));
        assertNextStep(
                new GameOfLife( "......",
                                "......",
                                "..***.",
                                ".***..",
                                "......",
                                "......"),
                new GameOfLife( "......",
                                "...*..",
                                ".*..*.",
                                ".*..*.",
                                "..*...",
                                "......"));
        assertNextStep(
                new GameOfLife( "......",
                                ".**...",
                                ".**...",
                                "...**.",
                                "...**.",
                                "......"),
                new GameOfLife( "......",
                                ".**...",
                                ".*....",
                                "....*.",
                                "...**.",
                                "......"));
    }

    private void assertNextStep(GameOfLife before, GameOfLife after){
        assertEquals(after, before.nextStep());
    }

}
