package bluestringpudding.core;

@FunctionalInterface
public interface Notifier {
	void notify(Id id, Event event);
}
