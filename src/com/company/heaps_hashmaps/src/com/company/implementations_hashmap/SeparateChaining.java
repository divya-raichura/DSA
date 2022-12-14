package com.company.implementations_hashmap;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Entry<K, V> {

    int hash;
    K key;
    V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
        this.hash = key.hashCode();
    }

    // We are not overriding the Object equals method
    // No casting is required with this method.
    public boolean equals(Entry<K, V> other) {
        if (hash != other.hash) return false; // if hash is equal then only
        // we compare if keys are equal or not (cause comparing hash is faster)
        return key.equals(other.key);
    }

    @Override
    public String toString() {
        return key + " => " + value;
    }
}

public class SeparateChaining<K, V> {

    private static final int DEFAULT_CAPACITY = 3;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private double maxLoadFactor;
    private int capacity, threshold, size = 0;
    private LinkedList<Entry<K, V>>[] table;

    public SeparateChaining() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public SeparateChaining(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    // Designated constructor
    public SeparateChaining(int capacity, double maxLoadFactor) {
        if (capacity < 0) throw new IllegalArgumentException("Illegal capacity");

        if (maxLoadFactor <= 0 || Double.isNaN(maxLoadFactor) || Double.isInfinite(maxLoadFactor))
            throw new IllegalArgumentException("Illegal maxLoadFactor");

        this.maxLoadFactor = maxLoadFactor;
        // buckets(table) size
        this.capacity = Math.max(DEFAULT_CAPACITY, capacity);
        // we don't want threshold (size/bucketsLen) to go beyond load factor
        threshold = (int) (this.capacity * maxLoadFactor);
        table = new LinkedList[this.capacity];
    }

    // Returns the number of elements currently inside the hash-table
    public int size() {
        return size;
    }

    // Returns true/false depending on whether the hash-table is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Converts a hash value to an index. Essentially, this strips the
    // negative sign and places the hash value in the domain [0, capacity)
    private int normalizeIndex(int keyHash) {
        // Math.abs(key.hashCode) % bucketLen
        return (keyHash & 0x7FFFFFFF) % capacity;
    }

    // Clears all the contents of the hash-table
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    public boolean containsKey(K key) {
        return hasKey(key);
    }

    // Returns true/false depending on whether a key is in the hash table
    public boolean hasKey(K key) {
        int bucketIndex = normalizeIndex(key.hashCode());
        return bucketSeekEntry(bucketIndex, key) != null;
    }

    // Finds and returns a particular entry in a given bucket if it exists, returns null otherwise
    private Entry<K, V> bucketSeekEntry(int bucketIndex, K key) {

        if (key == null) return null;
        LinkedList<Entry<K, V>> bucket = table[bucketIndex];
        if (bucket == null) return null;
        for (Entry<K, V> entry : bucket) if (entry.key.equals(key)) return entry;
        return null;
    }

    // Insert, put and add all place a value in the hash-table
    public V put(K key, V value) {
        return insert(key, value);
    }

//    public V insert(K key, V value) {
//        if (key == null) throw new IllegalArgumentException("Null key");
//        int bucketIndex = normalizeIndex(key.hashCode());
//        LinkedList<Entry<K, V>> entries = table[bucketIndex];
//        if (entries == null) return null;
//        for(Entry<K, V> e : entries) {
//            if (e.key.equals(key)) e.value = value;
//            return value;
//        }
//        entries.add(new Entry<>(key, value));
//        return value;
//    }

    public V insert(K key, V value) {

        if (key == null) throw new IllegalArgumentException("Null key");
        Entry<K, V> newEntry = new Entry<>(key, value);
        int bucketIndex = normalizeIndex(newEntry.hash);
        return bucketInsertEntry(bucketIndex, newEntry);
    }

    // Inserts an entry in a given bucket only if the entry does not already
    // exist in the given bucket, but if it does then update the entry value
    private V bucketInsertEntry(int bucketIndex, Entry<K, V> entry) {

        LinkedList<Entry<K, V>> bucket = table[bucketIndex];
        if (bucket == null) table[bucketIndex] = bucket = new LinkedList<>();

        Entry<K, V> existentEntry = bucketSeekEntry(bucketIndex, entry.key);
        if (existentEntry == null) {
            bucket.add(entry);
            if (++size > threshold) resizeTable();
            return null; // Use null to indicate that there was no previous entry
        } else {
            V oldVal = existentEntry.value;
            existentEntry.value = entry.value;
            return oldVal;
        }
    }

    // Gets a key's values from the map and returns the value.
    // NOTE: returns null if the value is null AND also returns
    // null if the key does not exists, so watch out..
    public V get(K key) {

        if (key == null) return null;
        int bucketIndex = normalizeIndex(key.hashCode());
        Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);
        if (entry != null) return entry.value;
        return null;
    }

    // Removes a key from the map and returns the value.
    // NOTE: returns null if the value is null AND also returns
    // null if the key does not exists.
    public V remove(K key) {

        if (key == null) return null;
        int bucketIndex = normalizeIndex(key.hashCode());
        return bucketRemoveEntry(bucketIndex, key);
    }

    // Removes an entry from a given bucket if it exists
    private V bucketRemoveEntry(int bucketIndex, K key) {

        Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);
        if (entry != null) {
            LinkedList<Entry<K, V>> links = table[bucketIndex];
            links.remove(entry);
            --size;
            return entry.value;
        } else return null;
    }

    // Resizes the internal table holding buckets of entries
    private void resizeTable() {
        System.out.println("resize");
        System.out.println(size());
        capacity *= 2;
        threshold = (int) (capacity * maxLoadFactor);

        LinkedList<Entry<K, V>>[] newTable = new LinkedList[capacity];

        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {

                for (Entry<K, V> entry : table[i]) {
                    int bucketIndex = normalizeIndex(entry.hash);
                    LinkedList<Entry<K, V>> bucket = newTable[bucketIndex];
                    if (bucket == null) newTable[bucketIndex] = bucket = new LinkedList<>();
                    bucket.add(entry);
                }

                // Avoid memory leak. Help the GC
                table[i].clear();
                table[i] = null;
            }
        }

        table = newTable;
    }

    // Returns the list of keys found within the hash table
    public List<K> keys() {

        List<K> keys = new ArrayList<>(size());
        for (LinkedList<Entry<K, V>> bucket : table)
            if (bucket != null) for (Entry<K, V> entry : bucket) keys.add(entry.key);
        return keys;
    }

    // Returns the list of values found within the hash table
    public List<V> values() {

        List<V> values = new ArrayList<>(size());
        for (LinkedList<Entry<K, V>> bucket : table)
            if (bucket != null) for (Entry<K, V> entry : bucket) values.add(entry.value);
        return values;
    }

    // Returns a string representation of this hash table
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < capacity; i++) {
            if (table[i] == null) continue;
            for (Entry<K, V> entry : table[i]) sb.append(entry).append(", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) {
        SeparateChaining<Integer, Integer> map = new SeparateChaining<>();
        map.put(1, 11);
        map.put(2, 12);
        map.put(3, 13);
        System.out.println(map.size());
        System.out.println(map);
        //        map.put(4, 14);
//        map.put(5, 15);
    }
}
