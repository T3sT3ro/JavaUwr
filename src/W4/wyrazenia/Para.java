package W4.wyrazenia;

public class Para {
    public final String klucz;
    private double wartość;

    public Para(String klucz, double wartość) {
        this.klucz = klucz;
        this.wartość = wartość;
    }

    public double getValue() {
        return wartość;
    }

    public void setValue(double wartość) {
        this.wartość = wartość;
    }

    @Override
    public String toString() {
        return "[ '" + klucz + "' , " + wartość + " ]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null | getClass() != obj.getClass()) return false;

        Para p = (Para) obj;
        return klucz.equals(p.klucz);
    }
}
