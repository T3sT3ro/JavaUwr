package W4.wyrazenia;

import org.jetbrains.annotations.NotNull;

public class Stala extends Wyrazenie {

    private final double value;

    Stala(@NotNull double value) {
        this.value = value;
    }

    @Override
    public double oblicz() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
