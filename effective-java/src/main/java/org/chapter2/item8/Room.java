package org.chapter2.item8;

import java.lang.ref.Cleaner;

/**
 * 8.避免使用终结方法(finalizer)和清除方法(cleaner)
 *
 * @author mawenhao 2022/10/29
 */
public class Room implements AutoCloseable{
    private static final Cleaner cleaner = Cleaner.create();

    private static class State implements Runnable {
        int numJunkPiles;

        State(int numJunkPiles) {
            this.numJunkPiles = numJunkPiles;
        }

        @Override
        public void run() {
            System.out.println("Cleaning room");
            numJunkPiles = 0;
        }
    }

    private final Cleaner.Cleanable cleanable;

    public Room(int numJunkPiles) {
        State state = new State(numJunkPiles);
        this.cleanable = cleaner.register(this, state);
    }

    @Override
    public void close() throws Exception {
        cleanable.clean();
    }

    public static void main(String[] args) throws Exception {
        Room room = new Room(10);
        room = null;
        System.gc();
        while (true){

        }
    }
}
