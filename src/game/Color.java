package src.game;

public enum Color {
    WHITE("W"), BLACK("B");

    private String identifier;

    Color(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return this.identifier;
    }
}
