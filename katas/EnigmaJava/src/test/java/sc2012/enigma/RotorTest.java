package sc2012.enigma;

import org.junit.Test;

import static junit.framework.Assert.*;

public class RotorTest {

    @Test
    public void shouldMapPositions() {
        final Rotor rotor = new Rotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ", "Q");
        assertEquals(20, rotor.connectionOf(0, true));
        assertEquals(0, rotor.connectionOf(20, false));
        assertEquals(12, rotor.connectionOf(14, true));
        assertEquals(14, rotor.connectionOf(12, false));
    }

    @Test
    public void shouldChangeMappingAfterMovingPositions() {
        final Rotor rotor = new Rotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ", "Q");
        assertEquals(20, rotor.connectionOf(0, true));
        rotor.moveOnePosition();
        assertEquals(21, rotor.connectionOf(0, true));
        rotor.moveOnePosition();
        assertEquals(22, rotor.connectionOf(0, true));
    }

    @Test
    public void shouldMoveToInitialPositionAfterFullCycle() {
        final Rotor rotor = new Rotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ", "Q");
        assertEquals(20, rotor.connectionOf(0, true));
        for (int i = 0; i < 26; i++) {
            rotor.moveOnePosition();
        }
        assertEquals(20, rotor.connectionOf(0, true));
    }

    @Test
    public void shouldTellWhenNotchPositionIsHit() {
        final Rotor rotor = new Rotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ", "Q");
        for (int i = 0; i < 15; i++) {
            assertFalse(rotor.moveOnePosition());
        }
        assertTrue(rotor.moveOnePosition());
    }

}
