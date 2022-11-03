import reactor.core.publisher.Mono;

public class GenerateUUIDTask {

	static UUIDGenerator uuidGenerator;

//	public static String generateRandomUUID() {
	public static Mono<String> generateRandomUUID() {
//		return uuidGenerator.secureUUID();
		// How to do it in Mono.fromRunnable?
//		return Mono.fromRunnable(Mono.just(uuidGenerator.secureUUID()));
		return Mono.defer(() -> Mono.just(uuidGenerator.secureUUID()));
	}
}