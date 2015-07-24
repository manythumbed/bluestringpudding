package bluestringpudding.core;

import bluestringpudding.core.testing.Down;
import bluestringpudding.core.testing.TestEvent;
import bluestringpudding.core.testing.TestRoot;
import bluestringpudding.core.testing.Up;
import com.google.common.collect.Lists;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class RootTest {

	@Test
	public void newRootShouldHaveNoChangesAndVersionZero() {
		final Root root = new TestRoot();

		assertThat(root.changes).isEmpty();
		assertThat(root.version).isEqualTo(Version.ZERO);
	}

	@Test
	public void shouldRecordChanges() {
		final TestRoot root = new TestRoot();

		root.increment();
		root.increment();
		root.decrement();

		assertThat(root.count()).isEqualTo(1);
		final Root base = root;
		assertThat(base.changes.size()).isEqualTo(3);
		assertThat(base.changes).containsExactly(new Up(1), new Up(1), new Down(1));
		assertThat(base.version).isEqualTo(Version.ZERO);
	}

	@Test
	public void shouldResetChangesOnSuccessfulStore()	{
		final TestRoot root = new TestRoot();

		root.increment();
		root.increment();
		root.decrement();

		assertThat(root.count()).isEqualTo(1);
		final Root base = root;
		assertThat(base.changes.size()).isEqualTo(3);
		assertThat(base.version).isEqualTo(Version.ZERO);

		base.storeSucceeded(new Version(3));

		assertThat(base.changes).isEmpty();
		assertThat(base.version).isEqualTo(new Version(3));
	}

	@Test
	public void shouldUpdateFromEventStream()	{
		final TestRoot root = new TestRoot();
		assertThat(root.count()).isEqualTo(0);

		final Root base = root;
		base.update(new EventStream(new Version(12), Lists.newArrayList(new Up(1), new Down(1), new Up(1), new Up(1))));

		assertThat(root.count()).isEqualTo(2);
		assertThat(base.version).isEqualTo(new Version(12));
		assertThat(base.changes).isEmpty();
	}

	@Test
	public void shouldIgnoreUnhandledEvents()	{
		final TestRoot root = new TestRoot();
		assertThat(root.count()).isEqualTo(0);

		final Root base = root;
		base.update(new EventStream(new Version(12), Lists.newArrayList(new TestEvent(), new TestEvent())));

		assertThat(root.count()).isEqualTo(0);
	}
}
