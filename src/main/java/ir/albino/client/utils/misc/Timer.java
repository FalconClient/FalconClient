package ir.albino.client.utils.misc;

import lombok.Getter;
import lombok.Setter;

import java.io.Closeable;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
public class Timer {
    private long currentMS = System.currentTimeMillis();
    private long resetMS = -1;

    public static java.util.Timer newTimer() {
        return new java.util.Timer();
    }

    public boolean hasReached(long miliseconds) {
        return this.hasReached(miliseconds, TimeUnit.MILLISECONDS);
    }

    public long hasLeft(long miliseconds) {
        return this.hasLeft(miliseconds, TimeUnit.MILLISECONDS);
    }

    public boolean hasReachedTick(long ticks) {
        return this.hasReached(ticks * 50);
    }

    public long hasLeftTick(long ticks) {
        return this.hasLeft(ticks * 50);
    }

    public boolean hasReached(long time, TimeUnit unit) {
        return System.currentTimeMillis() >= currentMS + unit.toMillis(time);
    }

    public long hasLeft(long time, TimeUnit unit) {
        return unit.toMillis(time) + currentMS - System.currentTimeMillis();
    }

    public long remaining() {
        return this.remaining(TimeUnit.MILLISECONDS);
    }

    public long remainingTick() {
        return this.remaining() / 50;
    }

    public long remaining(TimeUnit unit) {
        return unit.convert(System.currentTimeMillis() - currentMS, TimeUnit.MILLISECONDS);
    }

    public void reset() {
        currentMS = System.currentTimeMillis();
        resetMS = currentMS;
    }

    public TimeScheduler enter() {
        return new TimeScheduler();
    }

    @Getter
    @Setter
    public static class TimeScheduler implements Closeable {
        private final long start;
        private long done;
        private long reached;

        public TimeScheduler() {
            this.start = System.currentTimeMillis();
        }

        public boolean hasReached(long miliseconds) {
            return this.hasReached(miliseconds, TimeUnit.MILLISECONDS);
        }

        public boolean hasReachedTick(long ticks) {
            return this.hasReached(ticks * 50);
        }

        public boolean hasReached(long time, TimeUnit unit) {
            return System.currentTimeMillis() >= start + unit.toMillis(time);
        }

        public long getReached() {
            return Math.abs(System.currentTimeMillis() - start);
        }

        @Override
        public void close() {
            this.done = System.currentTimeMillis();
            this.reached = Math.abs(done - start);
        }
    }
}