
public class HashTable {

    long a;
    long c;
    long m;

    float counter;

    // public for testing purposes
    public int buckets[];

    public HashTable(long _a, long _c, long _m) {
        a = _a;
        c = _c;
        m = _m;

        buckets = new int[(int)m];
    }

    public void insert(int key) {

        int val = hashing(key);

        if (loadFactor()< 1) {

            while (buckets[val] > 0) {

                val = (val + 1) % (int) this.m;

            }
            buckets[val] = key;
            counter++;

        } else {

            extend();
            insert(key);
        }
    }

    public void extend(){

        int[] tempArray;
        tempArray = buckets;
        buckets = new int[(int) tempArray.length + 5];
        this.m += 5;

        for (int i = 0; i < (int) tempArray.length; i++) {

            int val = hashing(tempArray[i]);

            while (buckets[val] > 0) {

                val = (val + 1) % (int) this.m;
            }
            buckets[val] = tempArray[i];
        }
    }

    public boolean find(int key) {

        int val = hashing(key);

        for (int i = 0; i < m; i++) {

            if (buckets[val] == key) {

                return true;
            }
            val = (val + 1) % (int) this.m;
        }
        return false;
    }

    public void remove(int key) {

        int val = hashing(key);
        int startVal = val;

        while (buckets[val] != key) {

            val++;
        }

        if (find(key)) {

            buckets[val] = 0;

            if (hashing(buckets[val + 1]) == startVal) {

                buckets[startVal] = buckets[val + 1];
                buckets[val + 1] =- 1;
            }
        }
    }

    private int hashing(int x) {

        return (int) ((this.a * x + this.c) % (int) (this.m));
    }

    public double loadFactor() {
        //return 0.0;
        return counter/m;
    }

//    public static void main(String[] args) {
//
//        HashTable t = new HashTable(29, 35, 256);
//        t.insert(1);
//        System.out.println(buckets[]);
//    }
}
