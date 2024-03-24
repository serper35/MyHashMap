import java.util.Collection;
import java.util.Set;

public interface MyMap<K, V> {
    int size();

    boolean containKey(K key);

    boolean containValue(V value);

    boolean isEmpty();

    void put(K key, V value);

    V get(K key);

    void remove(K key);

    void clear();

    Set<K> keySet();

    Collection<V> values();

    Set<MyEntry<K, V>> entrySet();
}
