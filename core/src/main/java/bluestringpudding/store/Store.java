package bluestringpudding.store;

import bluestringpudding.core.Event;
import bluestringpudding.core.EventStream;
import bluestringpudding.core.Id;
import bluestringpudding.core.Version;

import java.util.List;

public interface Store {
	EventStream stream(Id id);
	EventStream stream(Id id, Version version);
	Status store(Id id, Version version, List<Event> events);
}
