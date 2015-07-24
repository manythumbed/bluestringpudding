package bluestringpudding.core.testing;

import bluestringpudding.core.Event;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;

public class Up extends Event {
	public final int amount;

	public Up(final int amount) {
		checkArgument(amount > 0);
		this.amount = amount;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final Up up = (Up) o;
		return Objects.equals(amount, up.amount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount);
	}
}
