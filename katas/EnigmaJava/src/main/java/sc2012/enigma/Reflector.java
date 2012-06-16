package sc2012.enigma;

public class Reflector {

    private final int[] mapping;

    public Reflector(final int[] mapping) {
        this.mapping = mapping;
    }

    public int reflect(final int position) {
        return mapping[position];
    }
}
