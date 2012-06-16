package sc2012.enigma;

public class EnigmaRunner {
    public static void main(String[] args) {
        final Enigma enigmaSender = createEnigma();
        final Enigma enigmaReceiver = createEnigma();
        final String input = "ENIGMAREVEALED";
        final String encryptedMessage = enigmaSender.encryptAll(input);
        System.out.println("    INPUT: " + input);
        System.out.println("ENCRYPTED: " + encryptedMessage);
        System.out.println("DECRYPTED: " + enigmaReceiver.encryptAll(encryptedMessage));
    }

    private static Enigma createEnigma() {
        final Rotor rotor1 = new Rotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ", "Q", "M");
        final Rotor rotor2 = new Rotor("AJDKSIRUXBLHWTMCQGZNPYFVOE", "E", "C");
        final Rotor rotor3 = new Rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO", "V", "K");
        final Reflector reflector = new Reflector(new int[]{24, 17, 20, 7, 16, 18, 11, 3, 15, 23, 13, 6, 14, 10, 12, 8, 4, 1, 5, 25, 2, 22, 21, 9, 0, 19});
        return new Enigma(reflector, rotor1, rotor2, rotor3);
    }
}
