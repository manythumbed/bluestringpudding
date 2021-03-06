package bluestringpudding.core;

import com.google.common.base.Strings;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

abstract public class Id {
	public final String value;

	protected Id(final String value) {
		checkNotNull(value);
		final String trimmed = value.trim();
		checkArgument(!Strings.isNullOrEmpty(trimmed));
		this.value = trimmed;
	}
}

