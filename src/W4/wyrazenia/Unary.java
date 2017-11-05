package W4.wyrazenia;

import org.jetbrains.annotations.NotNull;

public abstract class Unary extends Wyrazenie {

    protected final Wyrazenie w;

    public Unary(@NotNull Wyrazenie w) {
        this.w = w;
    }

    public abstract double oblicz();

    public abstract String toString();
}
