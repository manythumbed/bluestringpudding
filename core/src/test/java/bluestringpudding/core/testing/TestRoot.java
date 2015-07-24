package bluestringpudding.core.testing;

import bluestringpudding.core.Root;

public class TestRoot extends Root {
	private int eventCount = 0;

	@SuppressWarnings("unused")
	private void handle(final Up up)	{
		eventCount = eventCount + up.amount;
	}

	@SuppressWarnings("unused")
	private void handle(final Down down)	{
		eventCount = eventCount - down.amount;
	}

	public int count()	{
		return eventCount;
	}

	public void increment()	{
		record(new Up(1));
	}

	public void decrement()	{
		record(new Down(1));
	}
}