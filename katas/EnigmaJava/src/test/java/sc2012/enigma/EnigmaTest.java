package sc2012.enigma;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class EnigmaTest {

    @Test
    public void encryptionForSingleRotor() {
        final Rotor rotor = mock(Rotor.class);
        final Reflector reflector = mock(Reflector.class);
        when(rotor.connectionOf(4, false)).thenReturn(9);
        when(reflector.reflect(9)).thenReturn(23);
        when(rotor.connectionOf(23, true)).thenReturn(10);
        final Enigma enigma = new Enigma(reflector, rotor);
        assertEquals("K", enigma.encrypt("E"));
    }

    @Test
    public void decryptionForSingleRotor() {
        final Rotor rotor = mock(Rotor.class);
        final Reflector reflector = mock(Reflector.class);
        when(rotor.connectionOf(10, false)).thenReturn(23);
        when(reflector.reflect(23)).thenReturn(9);
        when(rotor.connectionOf(9, true)).thenReturn(4);
        final Enigma enigma = new Enigma(reflector, rotor);
        assertEquals("E", enigma.encrypt("K"));
    }

    @Test
    public void decryptionForSingleRotorSameCharacterTwice() {
        final Rotor rotor = mock(Rotor.class);
        final Reflector reflector = mock(Reflector.class);
        when(rotor.connectionOf(4, false)).thenReturn(9, 10);
        when(reflector.reflect(9)).thenReturn(23);
        when(reflector.reflect(10)).thenReturn(13);
        when(rotor.connectionOf(23, true)).thenReturn(10);
        when(rotor.connectionOf(13, true)).thenReturn(24);
        final Enigma enigma = new Enigma(reflector, rotor);
        assertEquals("K", enigma.encrypt("E"));
        assertEquals("Y", enigma.encrypt("E"));
    }

    @Test
    public void shouldMoveRotorOnEachEncryption() {
        final Rotor rotor = mock(Rotor.class);
        final Reflector reflector = mock(Reflector.class);
        final Enigma enigma = new Enigma(reflector, rotor);
        enigma.encrypt("E");
        enigma.encrypt("K");
        verify(rotor, times(2)).moveOnePosition();
    }

    @Test
    public void shouldMoveOnePositionOfSecondRotorIfFirstHitWindow() {
        final Rotor rotor1 = mock(Rotor.class);
        final Rotor rotor2 = mock(Rotor.class);
        final Reflector reflector = mock(Reflector.class);
        when(rotor1.moveOnePosition()).thenReturn(true);
        final Enigma enigma = new Enigma(reflector, rotor1, rotor2);
        enigma.encrypt("K");
        verify(rotor2, times(1)).moveOnePosition();
    }

    @Test
    public void shouldMoveOnePositionOfAllRotorsIfSecondMoves() {
        final Rotor rotor1 = mock(Rotor.class);
        final Rotor rotor2 = mock(Rotor.class);
        final Rotor rotor3 = mock(Rotor.class);
        final Reflector reflector = mock(Reflector.class);
        when(rotor1.moveOnePosition()).thenReturn(true);
        when(rotor2.moveOnePosition()).thenReturn(true);
        final Enigma enigma = new Enigma(reflector, rotor1, rotor2, rotor3);
        enigma.encrypt("K");
        verify(rotor3, times(1)).moveOnePosition();
    }

    @Test
    public void encryptionForThreeRotors() {
        final Rotor rotor1 = mock(Rotor.class);
        final Rotor rotor2 = mock(Rotor.class);
        final Rotor rotor3 = mock(Rotor.class);
        final Reflector reflector = mock(Reflector.class);
        when(rotor1.connectionOf(4, false)).thenReturn(19);
        when(rotor2.connectionOf(19, false)).thenReturn(22);
        when(rotor3.connectionOf(22, false)).thenReturn(9);
        when(reflector.reflect(9)).thenReturn(23);
        when(rotor3.connectionOf(23, true)).thenReturn(13);
        when(rotor2.connectionOf(13, true)).thenReturn(18);
        when(rotor1.connectionOf(18, true)).thenReturn(16);
        final Enigma enigma = new Enigma(reflector, rotor1, rotor2, rotor3);
        assertEquals("Q", enigma.encrypt("E"));
    }
}
