package bluestringpudding.store;

import bluestringpudding.core.Id;
import bluestringpudding.core.Root;

import java.util.Optional;

public interface Cache<T extends Root, I extends Id> {
	Optional<Snapshot<T>> fetch(I id);

	void store(I id, Snapshot<T> snapshot);
}
