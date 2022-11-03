import reactor.core.publisher.Flux;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CreateSequenceTask {

//	public static Iterable<Integer> createSequence() {
	public static Flux<Integer> createSequence() {
//		List<Integer> sequence = new ArrayList<>();
//		for (int i = 0; i < 20; i++) {
//			sequence.add(i);
//		}
//		return sequence;
		return Flux.range(0, 20);
	}
}