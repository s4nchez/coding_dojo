package sc2012.enigma;

public class Rotor {

    private String input = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String output;

    private final char inputCharacterAtNotchPosition;

    public Rotor(final String output,
                 final String notchPosition) {
        this(output, notchPosition, "A");
    }

    public Rotor(final String output,
                 final String characterAtNotchPosition,
                 final String initialCharacter) {
        this.output = output;
        this.inputCharacterAtNotchPosition = characterAtNotchPosition.charAt(0);
        int initialOffset = input.indexOf(initialCharacter);
        for (int i = 0; i < initialOffset; i++) {
            moveOnePosition();
        }
    }

    public int connectionOf(int position, final boolean reflecting) {
        if (reflecting) {
            final char charInput = input.charAt(position);
            return output.indexOf(String.valueOf(charInput));
        }
        final char charInput = output.charAt(position);
        return input.indexOf(String.valueOf(charInput));
    }


    public boolean moveOnePosition() {
        input = input.substring(1, input.length()) + input.charAt(0);
        output = output.substring(1, output.length()) + output.charAt(0);
        return input.charAt(0) == inputCharacterAtNotchPosition;
    }
}
