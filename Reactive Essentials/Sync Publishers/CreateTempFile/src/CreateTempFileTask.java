import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

import reactor.core.publisher.Mono;

public class CreateTempFileTask {

    //	public static File createTempFile(String prefix, String suffix) {
//		try {
// 			 return File.createTempFile(prefix, suffix);
// 		} catch(IOException e) {
// 			 throw new UncheckedIOException(e);
// 		}
//	}
    public static Mono<File> createTempFile(String prefix, String suffix) {
        return Mono.fromCallable(() -> File.createTempFile(prefix, suffix));
    }
}