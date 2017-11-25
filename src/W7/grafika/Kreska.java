package W7.grafika;

import java.awt.*;

public class Kreska extends Component {
    final Color kolor;
    Point poczatek, koniec;

    public Kreska(Point poczatek, Point koniec, Color kolor) {
        this.poczatek = poczatek;
        this.koniec = koniec;
        this.kolor = kolor;
    }

    public Kreska(Color kolor) {
        this.kolor = kolor;
    }
}
