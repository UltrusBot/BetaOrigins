package io.github.ultrusbot.origins.origin;

public enum Origin {
    EMPTY(0, "Empty"),
    HUMAN(1, "Human"),
    MERLING(2, "Merling"),
    ENDERIAN(3, "Enderian"),
    AVIAN(4, "Avian"),
    SHULK(5, "Shulk"),
    FELINE(6, "Feline"),
    BLAZEBORN(7, "Blazeborn"),
    ELYTRIAN(8, "Elytrian");

    public final int id;
    public final String name;
    Origin(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
