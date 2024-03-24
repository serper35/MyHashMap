import java.util.*;

/*A hashMap implementation by Vladislav Volkov
 *
 * parameter <K> a type of keys
 * parameter <V> a type of values*/

public class MyHashMap<K, V> implements MyMap<K, V> {


    /*Creating of MyHashMap*/
    private List<LinkedList<MyEntry<K, V>>> buckets;

    /*Initial size*/
    private int defaultCapacity = 16;

    /*initial load factor*/
    private final double LOAD_FACTOR = 0.75;

    /*Count the number of pairs added*/
    private int size = 0;

    /*Constructs a new MyHashMap with default capacity*/
    public MyHashMap() {
        buckets = new ArrayList<>(defaultCapacity);
        for (int i = 0; i < defaultCapacity; i++) {
            buckets.add(new LinkedList<>());
        }
    }


    /* Inserts the value with the key in this map. If the map
        previously contained the  key, the old value is replaced.*/
    @Override
    public void put(K key, V value) {

        increaseCapacity();

        int bucketIndex = getBucket(key);

        LinkedList<MyEntry<K, V>> bucket = buckets.get(bucketIndex);

        for (MyEntry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                entry.setKey(key);
                return;
            }
        }

        MyEntry<K, V> myEntry = new MyEntry<>(key, value);
        size++;
        bucket.add(myEntry);
    }

    /*Returns the value to which the  key is mapped, or null if this map
    contains no mapping for the key*/
    @Override
    public V get(K key) {
        int bucketIndex = getBucket(key);

        LinkedList<MyEntry<K, V>> bucket = buckets.get(bucketIndex);

        for (MyEntry<K, V> entry : bucket) {
            if (key.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    /*Deletes the value to which the  key is mapped*/
    @Override
    public void remove(K key) {
        int bucketIndex = getBucket(key);

        LinkedList<MyEntry<K, V>> bucket = buckets.get(bucketIndex);
        Iterator<MyEntry<K, V>> iterator = bucket.iterator();

        while (iterator.hasNext()) {
            MyEntry<K, V> entry = iterator.next();
            if (key.equals(entry.getKey())) {
                iterator.remove();
                size--;
                return;
            }
        }
    }

    /*Returns the size value*/
    @Override
    public int size() {
        return size;
    }

    /*Checks the key content of the map*/
    @Override
    public boolean containKey(K key) {
        int bucketIndex = getBucket(key);

        LinkedList<MyEntry<K, V>> bucket = buckets.get(bucketIndex);

        for (MyEntry<K, V> entry : bucket) {
            if (key.equals(entry.getKey())) {
                return true;
            }
        }
        return false;
    }

    /*Checks the value content of the map*/
    @Override
    public boolean containValue(V value) {
        for (LinkedList<MyEntry<K, V>> bucket : buckets) {
            for (MyEntry<K, V> entry : bucket) {
                if (entry.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    /*Checks if map is empty*/
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /*Deletes all values of map*/
    @Override
    public void clear() {
        buckets.clear();
        size = 0;
    }

    /*Returns set of all map's keys*/
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();

        for (LinkedList<MyEntry<K, V>> bucket : buckets) {
            for (MyEntry<K, V> entry : bucket) {
                keys.add(entry.getKey());
            }
        }

        return keys;
    }

    /*Returns collection of all map's values*/
    @Override
    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();

        for (LinkedList<MyEntry<K, V>> bucket : buckets) {
            for (MyEntry<K, V> entry : bucket) {
                values.add(entry.getValue());
            }
        }
        return values;
    }

    /*Returns set of all map's entries*/
    @Override
    public Set<MyEntry<K, V>> entrySet() {
        Set<MyEntry<K, V>> entries = new HashSet<>();

        for (LinkedList<MyEntry<K, V>> bucket : buckets) {
            entries.addAll(bucket);
        }

        return entries;
    }

    /*Gets index of bucket*/
    private int getBucket(K key) {
        return Math.abs(key.hashCode() % defaultCapacity);
    }

    /*map extension if load factor is greater than a given value*/
    public void increaseCapacity() {
        if ((double) size() / defaultCapacity > LOAD_FACTOR) {
            int newCapacity = (int) (defaultCapacity * 1.5 + 1);
            List<LinkedList<MyEntry<K, V>>> newBuckets = new ArrayList<>(newCapacity);

            for (int i = 0; i < newCapacity; i++) {
                newBuckets.add(new LinkedList<>());
            }

            for (LinkedList<MyEntry<K, V>> bucket : buckets) {
                for (MyEntry<K, V> entry : bucket) {
                    int newBucketIndex = Math.abs(entry.getKey().hashCode() % defaultCapacity);
                    newBuckets.get(newBucketIndex).add(entry);
                }
            }

            buckets = newBuckets;
            defaultCapacity = newCapacity;
        }
    }

    /*Overrides the toString method*/
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (LinkedList<MyEntry<K, V>> bucket : buckets) {
            for (MyEntry<K, V> entry : bucket) {
                stringBuilder.append(entry + ", ");
            }
        }
        return stringBuilder.toString();
    }
}
