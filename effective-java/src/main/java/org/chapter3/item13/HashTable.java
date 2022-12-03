package org.chapter3.item13;

import lombok.SneakyThrows;

import java.util.HashSet;
import java.util.TreeSet;

/**
 * 13 谨慎覆盖clone
 *
 * @author mawenhao 2022/11/18
 */
public class HashTable implements Cloneable {
    private Entry[] buckets;

    @Override
    protected HashTable clone() throws CloneNotSupportedException {
        HashTable result = (HashTable) super.clone();
        result.buckets = new Entry[buckets.length];
        Entry entry = buckets[0].deepCopy();
        for (int i = 0; i < buckets.length; i++) {
            result.buckets[i] = entry;
            entry = entry.next;
        }
        return result;
    }

//    @Override
//    protected HashTable clone() throws CloneNotSupportedException {
//        HashTable result = (HashTable) super.clone();
//        result.buckets = buckets.clone();
//        return result;
//    }

    private static class Entry {
        private final String key;
        private String value;
        private Entry next;

        public Entry(String key, String value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        private Entry deepCopy() {
            Entry result = new Entry(key, value, next);
            for (Entry p = result; p.next != null; p = p.next) {
                p.next = new Entry(p.next.key, p.next.value, p.next.next);
            }
            return result;
        }

//        private Entry deepCopy() {
//            Entry result = new Entry(key, value, null);
//            Entry entry = result;
//            while (next != null) {
//                entry.next = new Entry(next.key, next.value, null);
//                entry = entry.next;
//                next = next.next;
//            }
//            return result;
//        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key='" + key + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        HashTable table = new HashTable();
        Entry end = new Entry("end", "end", null);
        Entry mid = new Entry("mid", "mid", end);
        Entry head = new Entry("head", "head", mid);
        table.buckets = new Entry[]{head, mid, end};
        HashTable cloneTable = table.clone();
        System.out.println(table);
        System.out.println(cloneTable);
    }
}
