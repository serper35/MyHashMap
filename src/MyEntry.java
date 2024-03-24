import java.util.Objects;

public class MyEntry<K, V> {

    private K key;

    private V value;

    public MyEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || !(object instanceof MyEntry)) {
            return false;
        }

        MyEntry<K, V> myEntry = (MyEntry<K, V>) object;
        return this.key.equals(myEntry.key) && this.value.equals(myEntry.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return "{key=" + key +
                ", value=" + value +
                '}';
    }
}
