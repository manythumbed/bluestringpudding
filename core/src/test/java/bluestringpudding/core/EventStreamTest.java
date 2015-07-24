package bluestringpudding.core;

import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EventStreamTest {
	@Test
	public void shouldNotCreateEventStreamFromNullVersionOrList()	{
		assertThatThrownBy(() -> new EventStream(null, Collections.emptyList())).isInstanceOf(NullPointerException.class);
		assertThatThrownBy(() -> new EventStream(Version.ZERO, null)).isInstanceOf(NullPointerException.class);
	}

	@Test
	public void shouldCreateEventStream()	{
		final EventStream eventStream = new EventStream(Version.ZERO, Collections.emptyList());

		assertThat(eventStream.events).isNotNull().isEmpty();
		assertThat(eventStream.version).isEqualTo(Version.ZERO);
		assertThat(eventStream.exists()).isFalse();
	}

	@Test
	public void shouldCreateEmptyStream()	{
		final EventStream stream = EventStream.empty();

		assertThat(stream.version).isEqualTo(Version.ZERO);
		assertThat(stream.events).isEmpty();
		assertThat(stream.exists()).isFalse();
	}
}