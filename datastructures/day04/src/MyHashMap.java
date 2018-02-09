import java.util.*;

public class MyHashMap<K, V> implements Map<K, V> {

    // average number of entries per map before we grow the map
    private static final double ALPHA = 1.0;
    // average number of entries per map before we shrink the map
    private static final double BETA = .25;

    // resizing factor: (new size) = (old size) * (resize factor)
    private static final double SHRINK_FACTOR = 0.5, GROWTH_FACTOR = 2.0;

    private static final int MIN_BUCKETS = 16;

    // array of buckets
    protected LinkedList<Entry>[] buckets;
    private int size = 0;

    public MyHashMap() {
        initBuckets(MIN_BUCKETS);
    }

    public class Entry implements Map.Entry<K, V> {
        private K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            value = newValue;
            return value;
        }
    }

    /**
     * given a key, return the bucket where the `K, V` pair would be stored if it were in the map.
     */
    private LinkedList<Entry> chooseBucket(Object key) {
        // TODO
        // hint: use key.hashCode() to calculate the key's hashCode using its built in hash function
        // then use % to choose which bucket to return.

        return buckets[(key.hashCode() % buckets.length)];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * return true if key is in map
     */
    @Override
    public boolean containsKey(Object key) {
        // TODO
        /**
         * 1) Get the bucket the key would be in
         * 2) Search the bucket (using containsValue)
         * 3) Return a bool
         */
        LinkedList<Entry> bucket = chooseBucket(key);
        for(Entry e : bucket){
            if (e.getKey().equals(key)){
                return true;
            }
        }
        return false;
    }

    /**
     * return true if value is in map
     */
    @Override
    public boolean containsValue(Object value) {
        // TODO
        /**
         * Get the hash
         * Get the Bucket
         * Check if it contains the key
         */
        if (value == null){
            return true;
        }
        for(LinkedList<Entry> x : buckets){
            for (Entry e : x){
                if (e.getValue() != null && (e.getValue()).equals(value)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        // TODO
        /**
         * 1) Get the bucket
         * 2) Check the bucket
         * 3) Return null or V
         */
        LinkedList<Entry> target = chooseBucket(key);
        for (Entry x : target){
            if (x.getKey().equals(key)){
                return x.getValue();
            }
        }

        return null;
    }

    /**
     * add a new key-value pair to the map. Grow if needed, according to `ALPHA`.
     * @return the value associated with the key if it was previously in the map, otherwise null.
     */
    @Override
    public V put(K key, V value) {
        // TODO: Complete this method
        // hint: use chooseBucket() to determine which bucket to place the pair in
        // hint: use rehash() to appropriately grow the hashmap if needed
        LinkedList<Entry> target = chooseBucket(key);
        V oldVal;
        //Should I use containskey? Prob. not worth it
        try {
            for (Entry x : target) {
                if (x.getKey().equals(key)) {
                    oldVal = x.getValue();
                    x.setValue(value);
                    return oldVal;
                }
            }
        }
        catch (NullPointerException e) {

        }

        Entry stuff = new Entry(key, value);
        target.add(stuff);
        size++;
//        System.out.println(size);
//        System.out.println(value);
        if (size > buckets.length*ALPHA) {
            rehash(GROWTH_FACTOR);
        }
        return null;
    }

    /**
     * Remove the key-value pair associated with the given key. Shrink if needed, according to `BETA`.
     * Make sure the number of buckets doesn't go below `MIN_BUCKETS`. Do nothing if the key is not in the map.
     * @return the value associated with the key if it was in the map, otherwise null.
     */
    @Override
    public V remove(Object key) {
        // TODO
        // hint: use chooseBucket() to determine which bucket the key would be
        // hint: use rehash() to appropriately grow the hashmap if needed
        LinkedList<Entry> target = chooseBucket(key);
        Entry out = null;
        //Should I use containskey? Prob. not worth it
        for (Entry x : target){
            if (x.getKey().equals(key)){
                out = x;
                target.remove(x);
                System.out.println(out);
                break;
            }
        }
        size--;
        if (size <= buckets.length*BETA && buckets.length > MIN_BUCKETS){
//            System.out.println("Shrinking");
            rehash(SHRINK_FACTOR);
        }

        try {
            return out.getValue();
        }
        catch (NullPointerException e) {
            return null;
        }

    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Changes the number of buckets and rehashes the existing entries.
     * If growthFactor is 2, the number of buckets doubles. If it is 0.25,
     * the number of buckets is divided by 4.
     */
    private void rehash(double growthFactor) {
        // TODO
        // hint: once you have removed all values from the buckets, use put(k, v) to add them back in the correct place
        /**
         * 1) create a new number of buckets
         * 2) Extract all entries from buckets
         * 3) Re-add entries to new buckets as they're extracted
         */


        int newlen = (int)((buckets.length)*growthFactor);
        LinkedList<Entry>[] newBuckets = buckets;
        initBuckets(newlen);
//        System.out.println(size);
        for(int z = 0; z < newlen; z++){
            buckets[z] = new LinkedList<>();
        }
        size = 0;
        for(LinkedList<Entry> x : newBuckets){
            for (Entry y : x){
                put(y.getKey(), y.getValue());
            }
        }


    }

    private void initBuckets(int size) {
        buckets = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    public void clear() {
        initBuckets(buckets.length);
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Map.Entry<K, V> e : entrySet()) {
            keys.add(e.getKey());
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();
        for (Map.Entry<K, V> e : entrySet()) {
            values.add(e.getValue());
        }
        return values;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        for (LinkedList<Entry> map : buckets) {
            set.addAll(map);
        }
        return set;
    }
}