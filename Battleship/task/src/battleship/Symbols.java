package battleship;

public enum Symbols {
    O('O'),
    X('X'),
    M('M');

    private char value;

    Symbols(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}
