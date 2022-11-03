import reactor.core.publisher.Flux;

public class PropertiesSourceTask {

	static Properties settings;

	public static Flux<Property<?>> createSequence() {
//		return settings.asList();
		return Flux.fromIterable(settings.asList());
	}
}