package sc2012.enigma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Enigma {

    private final List<Rotor> rotors = new ArrayList<Rotor>();
    private final Reflector reflector;

    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Enigma(final Reflector reflector, final Rotor... rotors) {
        this.rotors.addAll(Arrays.asList(rotors));
        this.reflector = reflector;
    }

    public String encryptAll(final String input) {
        String result = "";
        for (int i = 0; i < input.length(); i++) {
            result += encrypt(String.valueOf(input.charAt(i)));
        }
        return result;
    }

    public String encrypt(final String input) {
        moveRotors();
        int currentPosition = LETTERS.indexOf(input);
        for (int i = rotors.size() - 1; i >= 0; i--) {
            currentPosition = rotors.get(i).connectionOf(currentPosition, false);
        }
        currentPosition = reflector.reflect(currentPosition);
        for (int i = 0; i < rotors.size(); i++) {
            currentPosition = rotors.get(i).connectionOf(currentPosition, true);
        }
        return String.valueOf(LETTERS.charAt(currentPosition));
    }

    private void moveRotors() {
        final Rotor rotor = rotors.get(rotors.size() - 1);
        boolean rotor1Moved = rotor.moveOnePosition();
        boolean rotor2Moved = false;
        if (rotors.size() >= 2 && rotor1Moved) {
            rotor2Moved = rotors.get(1).moveOnePosition();
        }
        if (rotors.size() == 3 && rotor2Moved) {
            rotors.get(0).moveOnePosition();
        }
    }
}
