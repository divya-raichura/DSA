package com.company.implementations_hashmap;

import java.util.ArrayList;
import java.util.List;

public class OpenAddressingQuadraticProbing<K, V> {
    private double loadFactor;
    private int capacity, threshold;

    // 'usedBuckets' counts the total number of used buckets inside the
    // hash-table (includes cells marked as deleted). While 'keyCount'
    // tracks the number of unique keys currently inside the hash-table.
    private int usedBuckets, keyCount;

    // These arrays store the key-value pairs.
    private K[] keys;
    private V[] values;

    // Special marker token used to indicate the deletion of a key-value pair
    private final K DELETED = (K) (new Object());

    private static final int DEFAULT_CAPACITY = 7;
    private static final double DEFAULT_LOAD_FACTOR = 0.65;

    public OpenAddressingQuadraticProbing() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public OpenAddressingQuadraticProbing(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    protected OpenAddressingQuadraticProbing(int capacity, double loadFactor) {
        if (capacity <= 0) throw new IllegalArgumentException("Illegal capacity: " + capacity);

        if (loadFactor <= 0 || Double.isNaN(loadFactor) || Double.isInfinite(loadFactor))
            throw new IllegalArgumentException("Illegal loadFactor: " + loadFactor);

        this.loadFactor = loadFactor;
        this.capacity = Math.max(DEFAULT_CAPACITY, nextPow2(capacity));
        threshold = (int) (this.capacity * loadFactor);

        keys = (K[]) new Object[this.capacity];
        values = (V[]) new Object[this.capacity];
    }

    private static int nextPow2(int n) {
        // making table capacity a power of 2
        int pow2 = Integer.highestOneBit(n); // returns a power of 2, specifically, a int number which has
        // 1 in msb and only one single 1 bit that too in msb all others 0, basically pow 2

        if (n == pow2) return n; // it is already pow of 2
        return Integer.highestOneBit(n) << 1; // make it pow of 2
    }

    private static int P(int x) {
        return (x * x + x) >> 1;
    }

    private int normalizeIndex(int keyHash) {
        return (keyHash & 0x7FFFFFFF) % capacity;
    }

    public void clear() {
        for (int i = 0; i < capacity; i++) {
            keys[i] = null;
            values[i] = null;
        }
        keyCount = usedBuckets = 0;
    }

    public int size() {
        return keyCount;
    }

    public boolean isEmpty() {
        return keyCount == 0;
    }

    public V put(K key, V value) {
        return insert(key, value);
    }

    // Place a key-value pair into the hash-table. If the value already
    // exists inside the hash-table then the value is updated.
    public V insert(K key, V value) {
        if (key == null) throw new IllegalArgumentException("Null key");
        if (usedBuckets >= threshold) resizeTable();

        final int hash = normalizeIndex(key.hashCode());
        int i = hash, j = -1, x = 1; // i for iterating, j is position of delete, x is for probe

        do {
            // current slot was previously deleted
            if (keys[i] == DELETED) {

                if (j == -1) j = i; // for optimisation purpose we do this

                // current cell already contains a key (collision)
            } else if (keys[i] != null) {

                // key we are trying to insert already exists in hash table
                // so update its value with most recent value
                if (keys[i].equals(key)) {

                    V oldValue = values[i];
                    if (j == -1) {
                        values[i] = value;
                    } else {
                        keys[i] = DELETED;
                        values[i] = null;
                        keys[j] = key;
                        values[j] = value;
                    }
                    return oldValue;

                }

                // current cell is null so insertion/update can occur
            } else {

                // no previously encountered deleted bucket
                if (j == -1) {
                    usedBuckets++; keyCount++;
                    keys[i] = key;
                    values[i] = value;

                    // previously seen deleted bucket. insert new element where deleted element
                    // was found instead of inserting it in i
                } else {
                    keyCount++;
                    keys[j] = key;
                    values[j] = value;
                }

                return null;

            }

            // collision, continue probing
            i = normalizeIndex(hash + P(x++));

        } while (true);
    }

    // Returns true/false on whether a given key exists within the hash-table
    public boolean hasKey(K key) {
        if (key == null) throw new IllegalArgumentException("Null key");

        final int hash = normalizeIndex(key.hashCode());
        int i = hash, j = -1, x = 1;

        // Start at the original hash value and probe until we find a spot where our key
        // is or hit a null element in which case our element does not exist.
        do {

            if (keys[i] == DELETED) {

                if (j == -1) j = i;

                // we hit a non-null key, perhaps it's the one we are looking for
            } else if (keys[i] != null) {

                // the key we want is in hash-table
                if (keys[i].equals(key)) {

                    // If j != -1 this means we previously encountered a deleted cell.
                    // We can perform an optimization by swapping the entries in cells
                    // i and j so that the next time we search for this key it will be
                    // found faster. This is called lazy deletion/relocation.
                    if (j != -1) {
                        // Swap the key-value pairs of positions i and j.
                        keys[j] = keys[i];
                        values[j] = values[i];
                        keys[i] = DELETED;
                        values[i] = null;
                    }

                    return true;
                }
            } else return false;

            i = normalizeIndex(hash + P(x++));

        } while (true);
    }

    // Get the value associated with the input key.
    // NOTE: returns null if the value is null AND also returns
    // null if the key does not exists.
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("Null key");

        final int hash = normalizeIndex(key.hashCode());
        int i = hash, j = -1, x = 1;

        // Start at the original hash value and probe until we find a spot where our key
        // is or we hit a null element in which case our element does not exist.
        do {

            // Ignore deleted cells, but record where the first index
            // of a deleted cell is found to perform lazy relocation later
            if (keys[i] == DELETED) {

                if (j == -1) j = i;

                // We hit a non-null key, perhaps it's the one we're looking for.
            } else if (keys[i] != null) {

                // The key we want is in the hash-table!
                if (keys[i].equals(key)) {


                    // If j != -1 this means we previously encountered a deleted cell.
                    // We can perform an optimization by swapping the entries in cells
                    // i and j so that the next time we search for this key it will be
                    // found faster. This is called lazy deletion/relocation.
                    if (j != -1) {

                        keys[j] = keys[i];
                        values[j] = values[i];
                        keys[i] = DELETED;
                        values[i] = null;

                        return values[j];

                    } else {
                        return values[i];
                    }

                }

                // Element was not found in the hash-table :/
            } else return null;

            i = normalizeIndex(hash + P(x++));

        } while (true);
    }

    // Removes a key from the map and returns the value.
    // NOTE: returns null if the value is null AND also returns
    // null if the key does not exist.
    public V remove(K key) {
        if (key == null) throw new IllegalArgumentException("Null key");

        final int hash = normalizeIndex(key.hashCode());
        int i = hash, x = 1;

        // Starting at the hash index quadratically probe until we find a spot where
        // our key is or we hit a null element in which case our element does not exist

        for (; ; i = normalizeIndex(hash + P(x++))) {

            // ignore deleted cells
            if (keys[i] == DELETED) continue;

            // key was not found in hashtable
            if (keys[i] == null) return null;

            // the key we want to remove is in hashtable
            if (keys[i].equals(key)) {

                keyCount--;
                V oldValue = values[i];
                keys[i] = DELETED;
                values[i] = null;
                return oldValue;

            }

        }
    }

    // Returns a list of keys found in the hash table
    public List<K> keySet() {
        List<K> keySet = new ArrayList<>(size());
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null && keys[i] != DELETED) {
                keySet.add(keys[i]);
            }
        }
        return keySet;
    }

    // Returns a list of non-unique values found in the hash table
    public List<V> valuesSet() {
        List<V> valuesSet = new ArrayList<>(size());
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null && keys[i] != DELETED) {
                valuesSet.add(values[i]);
            }
        }
        return valuesSet;
    }

    // Double the size of the hash-table
    private void resizeTable() {

        capacity *= 2;
        threshold = (int) (capacity * loadFactor);

        K[] oldKeyTable = (K[]) new Object[capacity];
        V[] oldValueTable = (V[]) new Object[capacity];

        // Perform key table pointer swap
        K[] keyTableTmp = keys;
        keys = oldKeyTable;
        oldKeyTable = keyTableTmp;

        // Perform value table pointer swap
        V[] valueTableTmp = values;
        values = oldValueTable;
        oldValueTable = valueTableTmp;

        // Reset the key count and buckets used since we are about to
        // re-insert all the keys into the hash-table.
        keyCount = usedBuckets = 0;

        for (int i = 0; i < oldKeyTable.length; i++) {
            if (oldKeyTable[i] != null && oldKeyTable[i] != DELETED) {
                insert(oldKeyTable[i], oldValueTable[i]);
            }
            oldValueTable[i] = null;
            oldKeyTable[i] = null;
        }

    }
}
