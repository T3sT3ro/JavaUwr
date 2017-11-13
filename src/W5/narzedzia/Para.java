package W5.narzedzia;

public class Para<K, V> {
    private final K key;
    private V value;

    public Para(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "['" +
                key +
                "', " + value +
                ']';
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Para && this.key.equals(((Para) obj).key);
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
