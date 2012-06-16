package sc2012.enigma;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ReflectorTest {

    @Test
    public void shouldMapPositions() {
        final Reflector rotor = new Reflector(new int[]{24, 17, 20, 7, 16, 18, 11, 3, 15, 23, 13, 6, 14, 10, 12, 8, 4, 1, 5, 25, 2, 22, 21, 9, 0, 19});
        assertEquals(23, rotor.reflect(9));
        assertEquals(9, rotor.reflect(23));
    }
}
