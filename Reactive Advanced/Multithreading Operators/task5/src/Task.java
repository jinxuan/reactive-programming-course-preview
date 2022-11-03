import java.util.concurrent.Callable;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class Task {

	public static Publisher<String> paralellizeLongRunningWorkOnUnboundedAmountOfThread(
			Flux<Callable<String>> streamOfLongRunningSources) {
//		return Flux.error(new ToDoException());
//		return streamOfLongRunningSources.publishOn(Schedulers.newBoundedElastic(256, 256, "256")).map(s -> {
//			try {
//				return s.call();
//			} catch (Exception e) {
//				throw new RuntimeException(e);
//			}
//		});

		Scheduler boundedElastic = Schedulers.newBoundedElastic(256, Integer.MAX_VALUE, "LocalBoundedElastic");
		return streamOfLongRunningSources.flatMap(call -> Mono.fromCallable(call).subscribeOn(boundedElastic));
	}
}