package W4.wyrazenia;

public class Print extends Wyrazenie {

    Object msg;

    public Print(Object msg) {
        if (msg == null)
            msg = "";
        this.msg = msg;
    }

    @Override
    public double oblicz() {
        System.out.println(msg instanceof Wyrazenie ? ((Wyrazenie)msg).oblicz() : msg);
        return 0;
    }

    @Override
    public String toString() {
        return "print(" + msg.toString() + ")";
    }
}
