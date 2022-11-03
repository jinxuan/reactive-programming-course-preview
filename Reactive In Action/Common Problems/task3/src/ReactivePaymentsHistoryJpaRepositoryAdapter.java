import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class ReactivePaymentsHistoryJpaRepositoryAdapter
		implements PaymentsHistoryReactiveJpaRepository {

	final PaymentsHistoryJpaRepository repository;
	Scheduler scheduler = Schedulers.newBoundedElastic(ConnectionsPool.instance().size(), Integer.MAX_VALUE, "schedulers");
	// use ExecutorService or Scheduler to offload blocking calls

	public ReactivePaymentsHistoryJpaRepositoryAdapter(PaymentsHistoryJpaRepository repository) {
		this.repository = repository;
	}

	public Flux<Payment> findAllByUserId(String userId) {
		// TODO: provide asynchronous wrapping around blocking JPARepository
		// HINT: Consider provide custom singleton thread-pool with limited amount of workers
		//       ThreadCount == ConnectionsPool.size()
		// My Solution:
		return Flux.just(userId)
				.subscribeOn(this.scheduler)
				.map(repository::findAllByUserId)
				.log()
				.flatMap(Flux::fromIterable);

//		return Flux.defer(() -> Flux.fromIterable(repository.findAllByUserId(userId)))
//				.subscribeOn(scheduler);
	}
}
