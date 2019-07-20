package como.taco.Events;

public class Timer {
	private long lastCheck = getSystemTime();

	public static long getSystemTime() {
		return System.nanoTime() / 1000000L;
	}

	public Timer() {

	}

	public boolean hasReached(int target) {
		return this.getTimePassed() >= (long) target;
	}

	public long getTimePassed() {
		return getSystemTime() - lastCheck;
	}

	public void reset() {
		lastCheck = getSystemTime();
	}

}
