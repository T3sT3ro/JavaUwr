package W4.wyrazenia;

import org.jetbrains.annotations.NotNull;

public abstract class Binary extends Unary {
    protected final Wyrazenie x;

    public Binary(@NotNull Wyrazenie w, @NotNull Wyrazenie x) {
        super(w);
        this.x = x;
    }

    public abstract double oblicz();

    public abstract String toString();
}
