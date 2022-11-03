import java.util.function.Function;
import java.util.stream.Collectors;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

public class Task {

    public static Publisher<Tuple2<Character, Integer>> groupWordsByFirstLatter(Flux<String> words) {
        return words.transform(Task::groupByFirstLetter).transform(Task::countLettersInWordsInGroup);
    }

    public static Flux<GroupedFlux<Character, String>> groupByFirstLetter(Flux<String> words) {
        return words.groupBy(s -> Character.valueOf(s.charAt(0)));
    }

    public static Flux<Tuple2<Character, Integer>> countLettersInWordsInGroup(Flux<GroupedFlux<Character, String>> groupedWords) {
        return groupedWords.flatMap(new Function<GroupedFlux<Character, String>, Publisher<Tuple2<Character, Integer>>>() {
            @Override
            public Publisher<Tuple2<Character, Integer>> apply(GroupedFlux<Character, String> characterStringGroupedFlux) {
                Mono<Tuple2<Character, Integer>> collect = characterStringGroupedFlux.map(word -> {
                            Character key = characterStringGroupedFlux.key();
                            return word.chars().filter(c -> c == key).count();
                        })
                        .collect(Collectors.summingInt(Long::intValue))
                        .map(count -> Tuples.of(characterStringGroupedFlux.key(), count));
                return collect;
            }
        });
    }
}